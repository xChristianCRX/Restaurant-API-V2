package com.restaurant.api.domain.orderHistory.useCases;

import com.restaurant.api.domain.orderHistory.OrderHistoryEntity;
import com.restaurant.api.domain.orderHistory.OrderHistoryRepository;
import com.restaurant.api.domain.orderHistory.dto.CreateOrderHistoryDTO;
import com.restaurant.api.domain.table.TableRepository;
import com.restaurant.api.domain.tableOrder.dto.CreateTableOrderDTO;
import com.restaurant.api.domain.tableOrder.useCases.CreateTableOrderUseCase;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class CreateOrderHistoryUseCase {
    private final TableRepository tableRepository;
    private final CreateTableOrderUseCase createTableOrderUseCase;
    private final OrderHistoryRepository orderHistoryRepository;

    public CreateOrderHistoryUseCase(TableRepository tableRepository, CreateTableOrderUseCase createTableOrderUseCase, OrderHistoryRepository orderHistoryRepository) {
        this.tableRepository = tableRepository;
        this.createTableOrderUseCase = createTableOrderUseCase;
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Transactional
    public OrderHistoryEntity execute(CreateOrderHistoryDTO data){
        var table = tableRepository.findById(data.tableNumber().getTableNumber())
                .orElseThrow(() -> new EntityNotFoundException("Table number not found!"));

        // Atualiza status da mesa para OCCUPIED
        table.setStatus(com.restaurant.api.domain.table.TableStatusENUM.OCCUPIED);
        tableRepository.save(table);

        var tableOrders = data.tableOrders().stream()
                .map(orderData -> createTableOrderUseCase.execute(new CreateTableOrderDTO(
                        orderData.getItem(),
                        orderData.getAdditions(),
                        orderData.getWaiter(),
                        orderData.getObservations()
                )))
                .toList();

        var orderHistory = new OrderHistoryEntity(table, tableOrders, true);
        return orderHistoryRepository.save(orderHistory);
    }
}
