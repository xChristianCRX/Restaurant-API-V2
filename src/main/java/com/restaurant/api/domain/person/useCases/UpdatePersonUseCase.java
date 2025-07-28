package com.restaurant.api.domain.person.useCases;

import com.restaurant.api.domain.person.PersonEntity;
import com.restaurant.api.domain.person.PersonRepository;
import com.restaurant.api.domain.person.PersonRoleENUM;
import com.restaurant.api.domain.person.dto.UpdatePersonDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdatePersonUseCase {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdatePersonUseCase(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PersonEntity execute(UpdatePersonDTO data) {
        System.out.println(data);
        var personOpt = personRepository.findById(data.id());
        if (personOpt.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        var person = personOpt.get();
        // Verifica se username ou email já existem para outro usuário
        personRepository.findByUsernameOrEmail(data.username(), data.email())
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
        return personRepository.save(person);
    }
}
