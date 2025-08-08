package com.restaurant.api.domain.tableOrder.dto;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.person.UserEntity;
import java.util.List;
import java.util.UUID;

public record UpdateTableOrderDTO(
    UUID id,
    MenuItemEntity item,
    List<AdditionEntity> additions,
    UserEntity waiter,
    String observations
) {} 