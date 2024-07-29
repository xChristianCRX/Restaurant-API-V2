package com.restaurant.api.domain.menu.dto;

import com.restaurant.api.domain.menu.TypeItemENUM;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record CreateMenuItemDTO(
        @NotBlank
        String name,

        @NotBlank
        Double price,

        @Enumerated(EnumType.STRING)
        TypeItemENUM type
    ) {
}
