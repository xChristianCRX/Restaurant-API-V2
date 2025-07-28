package com.restaurant.api.controller;

import com.restaurant.api.domain.person.dto.PersonDetailsDTO;
import com.restaurant.api.domain.table.TableEntity;
import com.restaurant.api.domain.table.TableRepository;
import com.restaurant.api.domain.table.dto.CreateTableDTO;
import com.restaurant.api.domain.table.useCases.CreateTableUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {
    private final CreateTableUseCase createTableUseCase;
    private final TableRepository repository;

    public TableController(CreateTableUseCase createTableUseCase, TableRepository repository) {
        this.createTableUseCase = createTableUseCase;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<TableEntity> createTable(@RequestBody @Valid CreateTableDTO dto, UriComponentsBuilder uriComponent) {
        var result = createTableUseCase.execute(dto);
        var uri = uriComponent.path("/table/{id}").buildAndExpand(result.getTableNumber()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping
    public ResponseEntity<List<TableEntity>> listAll(){
        var result = repository.findAll(Sort.by(Sort.Direction.ASC, "tableNumber"));
        return ResponseEntity.ok().body(result);
    }
}
