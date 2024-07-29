package com.restaurant.api.domain.person.dto;

import com.restaurant.api.domain.person.PersonRoleENUM;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreatePersonDTO(
        @NotBlank
        String name,

        @Enumerated(EnumType.STRING)
        PersonRoleENUM role,

        @NotBlank
        String username,

        @Email(message = "O campo deve conter um e-mail válido!")
        String email,

        @Length(min = 8, max = 100, message = "A senha deve conter entre 10 a 100 caracteres")
        String password
        ){
}
