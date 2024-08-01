package com.restaurant.api.controller;

import com.restaurant.api.domain.tableOrder.TableOrderEntity;
import com.restaurant.api.domain.tableOrder.TableOrderRepository;
import com.restaurant.api.domain.tableOrder.dto.CreateTableOrderDTO;
import com.restaurant.api.domain.tableOrder.useCases.CreateTableOrderUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Validated
@RequestMapping("/tableOrder")
public class TableOrderController {

    @Autowired
    private TableOrderRepository repository;

    @Autowired
    private CreateTableOrderUseCase createTableOrderUseCase;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateTableOrderDTO data, UriComponentsBuilder uriComponent){
        var tableOrder = createTableOrderUseCase.execute(data);
        repository.save(tableOrder);

        var uri = uriComponent.path("/tableOrder/{id}").buildAndExpand(tableOrder.getId()).toUri();
        return ResponseEntity.created(uri).body(tableOrder);
    }

    @GetMapping
    public ResponseEntity listAll(){
        var result = repository.findAll();
        return ResponseEntity.ok().body(result);
    }
}
