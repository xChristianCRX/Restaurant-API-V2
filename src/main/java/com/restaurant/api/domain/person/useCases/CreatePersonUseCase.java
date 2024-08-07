package com.restaurant.api.domain.person.useCases;

import com.restaurant.api.domain.person.PersonEntity;
import com.restaurant.api.domain.person.PersonRepository;
import com.restaurant.api.domain.person.dto.CreatePersonDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonUseCase {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PersonEntity execute(CreatePersonDTO data) {
        this.personRepository
                .findByUsernameOrEmail(data.username(), data.email())
                .ifPresent((user) -> {
                    throw new AlreadyExistException("Username or email already exists!");
                });
        var password = passwordEncoder.encode(data.password());
        System.out.println(password);
        var person = new PersonEntity(data);
        person.setPassword(password);

        return this.personRepository.save(person);
    }
}
