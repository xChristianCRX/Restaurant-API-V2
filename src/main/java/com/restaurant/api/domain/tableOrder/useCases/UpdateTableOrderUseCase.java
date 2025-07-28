package com.restaurant.api.domain.tableOrder.useCases;

import com.restaurant.api.domain.tableOrder.TableOrderEntity;
import com.restaurant.api.domain.tableOrder.TableOrderRepository;
import com.restaurant.api.domain.tableOrder.dto.UpdateTableOrderDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UpdateTableOrderUseCase {
    private final TableOrderRepository tableOrderRepository;

    public UpdateTableOrderUseCase(TableOrderRepository tableOrderRepository) {
        this.tableOrderRepository = tableOrderRepository;
    }

    @Transactional
    public TableOrderEntity execute(UpdateTableOrderDTO data) {
        TableOrderEntity tableOrder = tableOrderRepository.findById(data.id())
                .orElseThrow(() -> new EntityNotFoundException("TableOrder not found!"));
        tableOrder.setItem(data.item());
        tableOrder.setAdditions(data.additions());
        tableOrder.setWaiter(data.waiter());
        tableOrder.setObservations(data.observations());
        return tableOrderRepository.save(tableOrder);
    }
} 