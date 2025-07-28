package com.restaurant.api.domain.orderHistory;

import com.restaurant.api.domain.table.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, UUID> {
    Optional<OrderHistoryEntity> findByTableAndActiveIsTrue(TableEntity table);
    List<OrderHistoryEntity> findAllByTableAndActiveIsTrue(TableEntity table);
}
