package com.restaurant.api.controller;

import com.restaurant.api.domain.table.TableEntity;
import com.restaurant.api.domain.table.TableRepository;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableRepository repository;

    @PostMapping
    public ResponseEntity createTable(@RequestBody @Valid TableEntity table, UriComponentsBuilder uriComponent) {
        repository.findById(table.getTableNumber()).ifPresent(tableEntity -> {
            throw new AlreadyExistException("This table number already exists!");
        });

        repository.save(table);

        var uri = uriComponent.path("/table/{id}").buildAndExpand(table.getTableNumber()).toUri();
        return ResponseEntity.created(uri).body(table);
    }
}
