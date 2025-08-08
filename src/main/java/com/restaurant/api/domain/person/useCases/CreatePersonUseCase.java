package com.restaurant.api.domain.person.useCases;

import com.restaurant.api.domain.person.UserEntity;
import com.restaurant.api.domain.person.UserRepository;
import com.restaurant.api.domain.person.UserRoleENUM;
import com.restaurant.api.domain.person.dto.CreatePersonDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreatePersonUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity execute(CreatePersonDTO data) {
        this.userRepository
                .findByUsernameOrEmail(data.username(), data.email())
                .ifPresent((user) -> {
                    throw new AlreadyExistException("Username or e-mail already exists!");
                });
        String passwordHash = passwordEncoder.encode(data.password());
        UserEntity person = new UserEntity(
                data.name(),
                data.username(),
                data.email(),
                passwordHash,
                UserRoleENUM.WAITER
        );

        return userRepository.save(person);
    }
}
