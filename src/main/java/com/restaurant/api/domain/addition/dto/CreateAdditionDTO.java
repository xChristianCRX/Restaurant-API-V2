package com.restaurant.api.domain.addition.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAdditionDTO(

        @NotBlank
        String name,

        @NotNull
        Double price) {
}
