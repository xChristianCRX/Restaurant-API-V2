package com.restaurant.api.domain.orderHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, UUID> {
}
