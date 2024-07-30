package com.restaurant.api.domain.tableOrder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TableOrderRepository extends JpaRepository<TableOrderEntity, UUID> {
}
