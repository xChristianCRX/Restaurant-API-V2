package com.restaurant.api.domain.person.useCases;

import com.restaurant.api.domain.person.PersonEntity;
import com.restaurant.api.domain.person.PersonRepository;
import com.restaurant.api.domain.person.dto.CreatePersonDTO;
import com.restaurant.api.infra.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonUseCase {

    @Autowired
    private PersonRepository personRepository;

    public PersonEntity execute(CreatePersonDTO data) {
        this.personRepository
                .findByUsernameOrEmail(data.username(), data.email())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistException();
                });

        return this.personRepository.save(new PersonEntity(data));
    }
}
