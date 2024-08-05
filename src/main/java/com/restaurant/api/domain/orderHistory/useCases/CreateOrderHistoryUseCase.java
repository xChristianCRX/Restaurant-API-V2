package com.restaurant.api.domain.orderHistory.useCases;

import com.restaurant.api.domain.orderHistory.OrderHistoryEntity;
import com.restaurant.api.domain.orderHistory.dto.CreateOrderHistoryDTO;
import com.restaurant.api.domain.table.TableEntity;
import com.restaurant.api.domain.table.TableRepository;
import com.restaurant.api.domain.tableOrder.TableOrderRepository;
import com.restaurant.api.domain.tableOrder.dto.CreateTableOrderDTO;
import com.restaurant.api.domain.tableOrder.useCases.CreateTableOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateOrderHistoryUseCase {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private CreateTableOrderUseCase createTableOrderUseCase;

    @Autowired
    private TableOrderRepository tableOrderRepository;

    public OrderHistoryEntity execute(CreateOrderHistoryDTO data){
        var table = tableRepository.findById(data.tableNumber().getTableNumber()).orElse(new TableEntity());
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
