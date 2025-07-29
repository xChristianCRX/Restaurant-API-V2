package com.restaurant.api.domain.person.useCases;

import com.restaurant.api.domain.person.PersonEntity;
import com.restaurant.api.domain.person.PersonRepository;
import com.restaurant.api.domain.person.PersonRoleENUM;
import com.restaurant.api.domain.person.dto.CreatePersonDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonUseCase {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public CreatePersonUseCase(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PersonEntity execute(CreatePersonDTO data) {
        this.personRepository
                .findByUsernameOrEmail(data.username(), data.email())
                .ifPresent((user) -> {
                    throw new AlreadyExistException("Username or e-mail already exists!");
                });
        String passwordHash = passwordEncoder.encode(data.password());
        PersonEntity person = new PersonEntity(
                data.name(),
                data.username(),
                data.email(),
                passwordHash,
                PersonRoleENUM.WAITER
        );

        return personRepository.save(person);
    }
}
