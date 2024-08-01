package com.restaurant.api.controller;

import com.restaurant.api.domain.table.TableEntity;
import com.restaurant.api.domain.table.TableRepository;
import com.restaurant.api.domain.table.dto.CreateTableDTO;
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
    private TableRepository tableRepository;

    @PostMapping
    public ResponseEntity createTable(@RequestBody @Valid CreateTableDTO data, UriComponentsBuilder uriComponent) {
        var table = new TableEntity(data);
        tableRepository.save(table);

        var uri = uriComponent.path("/table/{id}").buildAndExpand(table.getTableNumber()).toUri();
        return ResponseEntity.created(uri).body(table);
    }
}
