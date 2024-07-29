package com.restaurant.api.controller;

import com.restaurant.api.domain.person.PersonEntity;
import com.restaurant.api.domain.person.PersonRepository;
import com.restaurant.api.domain.person.dto.CreatePersonDTO;
import com.restaurant.api.domain.person.dto.PersonDetailsDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RequestMapping("/person")
@Validated
@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@Valid @RequestBody CreatePersonDTO data, UriComponentsBuilder uriComponent){
        var person = new PersonEntity(data);
        repository.save(person);

        var uri = uriComponent.path("/person/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonDetailsDTO(person));
    }

    @GetMapping
    public ResponseEntity listAll(){
        var result = repository.findAll();
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
