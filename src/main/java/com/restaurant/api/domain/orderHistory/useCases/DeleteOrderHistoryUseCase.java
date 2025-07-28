package com.restaurant.api.domain.orderHistory.useCases;

import com.restaurant.api.domain.orderHistory.OrderHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteOrderHistoryUseCase {
    private final OrderHistoryRepository orderHistoryRepository;

    public DeleteOrderHistoryUseCase(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Transactional
    public void execute(UUID id) {
        if (!orderHistoryRepository.existsById(id)) {
            throw new EntityNotFoundException("OrderHistory not found!");
        }
        orderHistoryRepository.deleteById(id);
    }
} 