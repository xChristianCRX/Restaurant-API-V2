package com.restaurant.api.controller;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.addition.useCases.CreateAdditionUseCase;
import com.restaurant.api.domain.addition.dto.CreateAdditionDTO;
import com.restaurant.api.domain.addition.useCases.ListAdditionsUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/additions")
public class AdditionController {
    private final CreateAdditionUseCase createAdditionUseCase;
    private final ListAdditionsUseCase listAdditionsUseCase;

    public AdditionController(CreateAdditionUseCase createAdditionUseCase, ListAdditionsUseCase listAdditionsUseCase) {
        this.createAdditionUseCase = createAdditionUseCase;
        this.listAdditionsUseCase = listAdditionsUseCase;
    }

    @PostMapping
    public ResponseEntity<AdditionEntity> create(@Valid @RequestBody CreateAdditionDTO data, UriComponentsBuilder uriComponent){
        var addition = createAdditionUseCase.execute(data);
        var uri = uriComponent.path("/additions/{id}").buildAndExpand(addition.getId()).toUri();
        return ResponseEntity.created(uri).body(addition);
    }

    @GetMapping
    public ResponseEntity<Page<AdditionEntity>> listAll(@RequestParam(required = false) String name, Pageable pageable) {
        Page<AdditionEntity> page = listAdditionsUseCase.execute(name, pageable);
        return ResponseEntity.ok(page);
    }
}
