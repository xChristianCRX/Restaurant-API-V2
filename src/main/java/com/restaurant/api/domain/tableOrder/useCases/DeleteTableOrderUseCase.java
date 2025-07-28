package com.restaurant.api.domain.tableOrder.useCases;

import com.restaurant.api.domain.tableOrder.TableOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteTableOrderUseCase {
    private final TableOrderRepository tableOrderRepository;

    public DeleteTableOrderUseCase(TableOrderRepository tableOrderRepository) {
        this.tableOrderRepository = tableOrderRepository;
    }

    @Transactional
    public void execute(UUID id) {
        if (!tableOrderRepository.existsById(id)) {
            throw new EntityNotFoundException("TableOrder not found!");
        }
        tableOrderRepository.deleteById(id);
    }
} 