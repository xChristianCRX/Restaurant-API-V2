package com.restaurant.api.domain.table;

import com.restaurant.api.domain.person.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Integer> {
    Optional<TableEntity> findById(Integer number);
}
