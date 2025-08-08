package com.restaurant.api.domain.addition;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdditionRepository extends JpaRepository<AdditionEntity, UUID> {
    Optional<AdditionEntity> findByName(String name);

    @Query("SELECT a FROM Addition a WHERE " +
            "(:name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<AdditionEntity> findByFilters(
            @Param("name") String name,
            Pageable pageable);
}
