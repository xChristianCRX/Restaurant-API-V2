package com.restaurant.api.domain.person.dto;

import com.restaurant.api.domain.person.PersonEntity;
import com.restaurant.api.domain.person.PersonRoleENUM;

import java.util.Optional;
import java.util.UUID;

public record PersonDetailsDTO(UUID id, String name, PersonRoleENUM role, String username, String email) {

    public PersonDetailsDTO(PersonEntity person){
        this(
                person.getId(),
                person.getName(),
                person.getRole(),
                person.getUsername(),
                person.getEmail()
        );
    }

    public PersonDetailsDTO(Optional<PersonEntity> person){
        this(
                person.get().getId(),
                person.get().getName(),
                person.get().getRole(),
                person.get().getUsername(),
                person.get().getEmail()
        );
    }
}
