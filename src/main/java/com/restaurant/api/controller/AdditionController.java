package com.restaurant.api.controller;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.addition.AdditionRepository;
import com.restaurant.api.domain.addition.dto.CreateAdditionDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/addition")
public class AdditionController {

    @Autowired
    private AdditionRepository repository;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CreateAdditionDTO data, UriComponentsBuilder uriComponent){
        var addition = new AdditionEntity(data);
        repository.save(addition);

        var uri = uriComponent.path("/addition/{id}").buildAndExpand(addition.getId()).toUri();
        return ResponseEntity.created(uri).body(addition);
    }

    @GetMapping
    public ResponseEntity listAll(){
        var result = repository.findAll();
        return ResponseEntity.ok().body(result);
    }

}
