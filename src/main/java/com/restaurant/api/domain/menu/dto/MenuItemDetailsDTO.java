package com.restaurant.api.domain.menu.dto;

import com.restaurant.api.domain.menu.TypeItemENUM;

import java.util.UUID;

public record MenuItemDetailsDTO(UUID id, String name, String description, Double price, TypeItemENUM type) {


}
