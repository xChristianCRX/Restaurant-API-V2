package com.restaurant.api.domain.tableOrder.dto;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.person.UserEntity;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateTableOrderDTO(
        @NotNull
        MenuItemEntity item,

        List<AdditionEntity> additions,

        @NotNull
        UserEntity waiter,

        String observations
    ) {
}
