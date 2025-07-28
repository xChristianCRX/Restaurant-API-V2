package com.restaurant.api.domain.orderHistory.dto;

import com.restaurant.api.domain.tableOrder.TableOrderEntity;
import java.util.List;
import java.util.UUID;

public record UpdateOrderHistoryDTO(
    UUID id,
    List<TableOrderEntity> tableOrders,
    Boolean active
) {} 