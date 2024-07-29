package com.restaurant.api.domain.addition.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAdditionDTO(

        @NotBlank
        String name,

        @NotBlank
        Double price) {
}
