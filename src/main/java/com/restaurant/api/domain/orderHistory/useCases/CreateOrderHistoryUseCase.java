package com.restaurant.api.domain.orderHistory.useCases;

import com.restaurant.api.domain.orderHistory.OrderHistoryEntity;
import com.restaurant.api.domain.orderHistory.dto.CreateOrderHistoryDTO;
import com.restaurant.api.domain.table.TableEntity;
import com.restaurant.api.domain.table.TableRepository;
import com.restaurant.api.domain.tableOrder.dto.CreateTableOrderDTO;
import com.restaurant.api.domain.tableOrder.useCases.CreateTableOrderUseCase;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateOrderHistoryUseCase {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CreateTableOrderUseCase createTableOrderUseCase;

    public OrderHistoryEntity execute(CreateOrderHistoryDTO data){
        var table = tableRepository.findById(data.tableNumber().getTableNumber())
                .orElseThrow(() -> new EntityNotFoundException("Table number not found!"));
        var tableOrders = data.tableOrders().stream()
                .map(tableOrderEntity -> {
                    var dto = new CreateTableOrderDTO(
                            tableOrderEntity.getItem(),
                            tableOrderEntity.getAdditions(),
                            tableOrderEntity.getWaiter(),
                            tableOrderEntity.getObservations()
                    );
                    var tableOrder = createTableOrderUseCase.execute(dto);
                    return tableOrder;
                }).toList();
        Boolean active = true;

        return new OrderHistoryEntity(table, tableOrders, active);
    }
}
