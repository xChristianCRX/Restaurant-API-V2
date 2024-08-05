package com.restaurant.api.domain.tableOrder.dto;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.menu.dto.MenuItemDetailsDTO;
import com.restaurant.api.domain.person.dto.WaiterDetailsDTO;
import com.restaurant.api.domain.tableOrder.TableOrderEntity;

import java.util.List;
import java.util.UUID;

public record TableOrderDetailsDTO(UUID id, MenuItemDetailsDTO item, List<AdditionEntity> additions, WaiterDetailsDTO waiter, String observations) {

    public TableOrderDetailsDTO(TableOrderEntity tableOrder){
        this(
                tableOrder.getId(),
                new MenuItemDetailsDTO(tableOrder.getItem().getName(),tableOrder.getItem().getPrice()),
                tableOrder.getAdditions(),
                new WaiterDetailsDTO(tableOrder.getWaiter().getName()),
                tableOrder.getObservations()
        );
    }
}
