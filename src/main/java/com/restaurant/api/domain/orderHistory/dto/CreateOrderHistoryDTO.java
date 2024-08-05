package com.restaurant.api.domain.orderHistory.dto;

import com.restaurant.api.domain.table.TableEntity;
import com.restaurant.api.domain.tableOrder.TableOrderEntity;

import java.util.List;

public record CreateOrderHistoryDTO(
        TableEntity tableNumber,

        List<TableOrderEntity> tableOrders
    ) {
}
