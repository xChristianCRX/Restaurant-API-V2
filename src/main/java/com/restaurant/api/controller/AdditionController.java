package com.restaurant.api.controller;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.addition.AdditionRepository;
import com.restaurant.api.domain.addition.useCases.CreateAdditionUseCase;
import com.restaurant.api.domain.addition.dto.CreateAdditionDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/addition")
public class AdditionController {
    private final AdditionRepository repository;
    private final CreateAdditionUseCase createAdditionUseCase;

    public AdditionController(AdditionRepository repository, CreateAdditionUseCase createAdditionUseCase) {
        this.repository = repository;
        this.createAdditionUseCase = createAdditionUseCase;
    }

    @PostMapping
    public ResponseEntity<AdditionEntity> create(@Valid @RequestBody CreateAdditionDTO data, UriComponentsBuilder uriComponent){
        var addition = createAdditionUseCase.execute(data);
        repository.save(addition);

        var uri = uriComponent.path("/addition/{id}").buildAndExpand(addition.getId()).toUri();
        return ResponseEntity.created(uri).body(addition);
    }

    @GetMapping
    public ResponseEntity<List<AdditionEntity>> listAll(){
        var result = repository.findAll();
        return ResponseEntity.ok().body(result);
    }

}
