package com.restaurant.api.controller;

import com.restaurant.api.domain.orderHistory.dto.CreateOrderHistoryDTO;
import com.restaurant.api.domain.orderHistory.dto.OrderHistoryDetailsDTO;
import com.restaurant.api.domain.orderHistory.useCases.CreateOrderHistoryUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/orders")
public class OrderHistoryController {
    private final CreateOrderHistoryUseCase createOrderHistoryUseCase;

    public OrderHistoryController(CreateOrderHistoryUseCase createOrderHistoryUseCase) {
        this.createOrderHistoryUseCase = createOrderHistoryUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderHistoryDetailsDTO> createOrder(@Valid @RequestBody CreateOrderHistoryDTO data, UriComponentsBuilder uriComponent){
        var orderHistory = createOrderHistoryUseCase.execute(data);
        var uri = uriComponent.path("/orders/{id}").buildAndExpand(orderHistory.getId()).toUri();
        return ResponseEntity.created(uri).body(new OrderHistoryDetailsDTO(orderHistory));
    }
}
