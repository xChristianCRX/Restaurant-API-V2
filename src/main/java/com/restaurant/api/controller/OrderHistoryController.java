package com.restaurant.api.controller;

import com.restaurant.api.domain.orderHistory.dto.CreateOrderHistoryDTO;
import com.restaurant.api.domain.orderHistory.dto.OrderHistoryDetailsDTO;
import com.restaurant.api.domain.orderHistory.useCases.CreateOrderHistoryUseCase;
import com.restaurant.api.domain.orderHistory.OrderHistoryEntity;
import com.restaurant.api.domain.orderHistory.OrderHistoryRepository;
import com.restaurant.api.domain.table.TableRepository;
import com.restaurant.api.domain.table.TableStatusENUM;
import com.restaurant.api.domain.orderHistory.dto.UpdateOrderHistoryDTO;
import com.restaurant.api.domain.orderHistory.useCases.UpdateOrderHistoryUseCase;
import com.restaurant.api.domain.orderHistory.useCases.DeleteOrderHistoryUseCase;
import com.restaurant.api.domain.tableOrder.dto.UpdateTableOrderDTO;
import com.restaurant.api.domain.tableOrder.useCases.UpdateTableOrderUseCase;
import com.restaurant.api.domain.tableOrder.useCases.DeleteTableOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/orders")
public class OrderHistoryController {
    private final CreateOrderHistoryUseCase createOrderHistoryUseCase;
    private final OrderHistoryRepository orderHistoryRepository;
    private final TableRepository tableRepository;
    private final UpdateOrderHistoryUseCase updateOrderHistoryUseCase;
    private final DeleteOrderHistoryUseCase deleteOrderHistoryUseCase;
    private final UpdateTableOrderUseCase updateTableOrderUseCase;
    private final DeleteTableOrderUseCase deleteTableOrderUseCase;

    public OrderHistoryController(
        CreateOrderHistoryUseCase createOrderHistoryUseCase,
        OrderHistoryRepository orderHistoryRepository,
        TableRepository tableRepository,
        UpdateOrderHistoryUseCase updateOrderHistoryUseCase,
        DeleteOrderHistoryUseCase deleteOrderHistoryUseCase,
        UpdateTableOrderUseCase updateTableOrderUseCase,
        DeleteTableOrderUseCase deleteTableOrderUseCase
    ) {
        this.createOrderHistoryUseCase = createOrderHistoryUseCase;
        this.orderHistoryRepository = orderHistoryRepository;
        this.tableRepository = tableRepository;
        this.updateOrderHistoryUseCase = updateOrderHistoryUseCase;
        this.deleteOrderHistoryUseCase = deleteOrderHistoryUseCase;
        this.updateTableOrderUseCase = updateTableOrderUseCase;
        this.deleteTableOrderUseCase = deleteTableOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderHistoryDetailsDTO> createOrder(@Valid @RequestBody CreateOrderHistoryDTO data, UriComponentsBuilder uriComponent){
        var orderHistory = createOrderHistoryUseCase.execute(data);
        var uri = uriComponent.path("/orders/{id}").buildAndExpand(orderHistory.getId()).toUri();
        return ResponseEntity.created(uri).body(new OrderHistoryDetailsDTO(orderHistory));
    }

    @GetMapping("/active/{tableNumber}")
    public ResponseEntity<?> getActiveOrdersByTable(@PathVariable Integer tableNumber) {
        var tableOpt = tableRepository.findById(tableNumber);
        if (tableOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var orders = orderHistoryRepository.findAllByTableAndActiveIsTrue(tableOpt.get());
        if (orders.isEmpty()) {
            return ResponseEntity.ok().body(List.of());
        }
        var dtos = orders.stream().map(OrderHistoryDetailsDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderHistoryDetailsDTO> updateOrderHistory(@PathVariable UUID id, @RequestBody UpdateOrderHistoryDTO data) {
        var updated = updateOrderHistoryUseCase.execute(data);
        return ResponseEntity.ok(new OrderHistoryDetailsDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderHistory(@PathVariable UUID id) {
        deleteOrderHistoryUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/table-order/{id}")
    public ResponseEntity<?> updateTableOrder(@PathVariable UUID id, @RequestBody UpdateTableOrderDTO data) {
        var updated = updateTableOrderUseCase.execute(data);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/table-order/{id}")
    public ResponseEntity<Void> deleteTableOrder(@PathVariable UUID id) {
        deleteTableOrderUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/finish/{tableNumber}")
    public ResponseEntity<Void> finishOrder(@PathVariable Integer tableNumber) {
        var tableOpt = tableRepository.findById(tableNumber);
        if (tableOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        var orders = orderHistoryRepository.findAllByTableAndActiveIsTrue(tableOpt.get());
        for (OrderHistoryEntity order : orders) {
            order.setActive(false);
            orderHistoryRepository.save(order);
        }
        
        var table = tableOpt.get();
        table.setStatus(TableStatusENUM.AVAILABLE);
        tableRepository.save(table);
        
        return ResponseEntity.ok().build();
    }
}
