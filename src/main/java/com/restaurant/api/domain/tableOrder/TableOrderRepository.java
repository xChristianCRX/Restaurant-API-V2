package com.restaurant.api.domain.tableOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TableOrderRepository extends JpaRepository<TableOrderEntity, UUID> {
}
