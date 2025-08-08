package com.restaurant.api.domain.person.useCases;

import com.restaurant.api.domain.person.UserEntity;
import com.restaurant.api.domain.person.UserRepository;
import com.restaurant.api.domain.person.dto.UpdatePersonDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdatePersonUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity execute(UpdatePersonDTO data) {
        System.out.println(data);
        var personOpt = userRepository.findById(data.id());
        if (personOpt.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        var person = personOpt.get();
        // Verifica se username ou email já existem para outro usuário
        userRepository.findByUsernameOrEmail(data.username(), data.email())
                .filter(u -> !u.getId().equals(person.getId()))
                .ifPresent((user) -> {
                    throw new AlreadyExistException("Username ou e-mail já existem!");
                });
        person.setName(data.name());
        person.setUsername(data.username());
        person.setEmail(data.email());
        if (data.password() != null && !data.password().isBlank()) {
            person.setPassword(passwordEncoder.encode(data.password()));
        }
        if (data.role() != null) {
            person.setRole(data.role());
        }
        return userRepository.save(person);
    }
}
