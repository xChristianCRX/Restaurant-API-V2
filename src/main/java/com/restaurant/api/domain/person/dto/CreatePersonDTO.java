package com.restaurant.api.domain.person.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePersonDTO(
        @NotBlank String name,
        @NotBlank String username,
        @Email String email,
        @Size(min = 8, max = 100) String password
) { }
