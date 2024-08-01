package com.restaurant.api.domain.table.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTableDTO(
        @NotNull
        Integer tableNumber

    ) {
}
