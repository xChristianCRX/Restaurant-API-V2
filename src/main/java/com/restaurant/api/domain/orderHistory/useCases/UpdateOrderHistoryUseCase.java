package com.restaurant.api.domain.orderHistory.useCases;

import com.restaurant.api.domain.orderHistory.OrderHistoryEntity;
import com.restaurant.api.domain.orderHistory.OrderHistoryRepository;
import com.restaurant.api.domain.orderHistory.dto.UpdateOrderHistoryDTO;
import com.restaurant.api.domain.tableOrder.TableOrderEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UpdateOrderHistoryUseCase {
    private final OrderHistoryRepository orderHistoryRepository;

    public UpdateOrderHistoryUseCase(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Transactional
    public OrderHistoryEntity execute(UpdateOrderHistoryDTO data) {
        OrderHistoryEntity orderHistory = orderHistoryRepository.findById(data.id())
                .orElseThrow(() -> new EntityNotFoundException("OrderHistory not found!"));
        orderHistory.setTableOrders(data.tableOrders());
        orderHistory.setActive(data.active());
        return orderHistoryRepository.save(orderHistory);
    }
} 