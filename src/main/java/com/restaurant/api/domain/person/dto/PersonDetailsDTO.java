package com.restaurant.api.domain.person.dto;

import com.restaurant.api.domain.person.UserEntity;
import com.restaurant.api.domain.person.UserRoleENUM;

import java.util.Optional;
import java.util.UUID;

public record PersonDetailsDTO(UUID id, String name, UserRoleENUM role, String username, String email) {

    public PersonDetailsDTO(UserEntity person){
        this(
                person.getId(),
                person.getName(),
                person.getRole(),
                person.getUsername(),
                person.getEmail()
        );
    }

    public PersonDetailsDTO(Optional<UserEntity> person){
        this(
                person.get().getId(),
                person.get().getName(),
                person.get().getRole(),
                person.get().getUsername(),
                person.get().getEmail()
        );
    }
}
