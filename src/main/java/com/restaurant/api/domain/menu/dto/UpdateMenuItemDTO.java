package com.restaurant.api.domain.menu.dto;

import com.restaurant.api.domain.menu.TypeItemENUM;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record UpdateMenuItemDTO(
    @NotNull
    UUID id,
    @NotBlank
    String name,
    String description,
    @NotNull
    Double price,
    @Enumerated(EnumType.STRING)
    TypeItemENUM type
) {}
