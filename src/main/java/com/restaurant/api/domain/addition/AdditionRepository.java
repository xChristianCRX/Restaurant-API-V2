package com.restaurant.api.domain.addition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdditionRepository extends JpaRepository<AdditionEntity, UUID> {
    Optional<AdditionEntity> findByName(String name);
}
