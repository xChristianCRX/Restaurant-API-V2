package com.restaurant.api.domain.person.dto;

import com.restaurant.api.domain.person.UserRoleENUM;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UpdatePersonDTO(
        @NotNull UUID id,
        @NotBlank String name,
        @NotBlank String username,
        @Email String email,
        @Size(min = 8, max = 100) String password,
        UserRoleENUM role
) { }
