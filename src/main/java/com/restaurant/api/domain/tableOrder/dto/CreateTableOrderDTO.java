package com.restaurant.api.domain.tableOrder.dto;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.person.PersonEntity;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateTableOrderDTO(
        @NotNull
        MenuItemEntity item,

        @NotNull
        PersonEntity waiter,

        List<AdditionEntity> additions,

        String observations
    ) {
}
