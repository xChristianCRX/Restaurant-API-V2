package com.restaurant.api.domain.orderHistory.dto;

import com.restaurant.api.domain.orderHistory.OrderHistoryEntity;
import com.restaurant.api.domain.tableOrder.dto.TableOrderDetailsDTO;

import java.util.List;
import java.util.UUID;

public record OrderHistoryDetailsDTO (UUID id, List<TableOrderDetailsDTO> tableOrders, Boolean active){
    public OrderHistoryDetailsDTO(OrderHistoryEntity orderHistory){
        this(
                orderHistory.getId(),
                orderHistory.getTableOrders().stream().map(TableOrderDetailsDTO::new).toList(),
                orderHistory.getActive()
        );
    }
}
