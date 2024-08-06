package com.restaurant.api.controller;

import com.restaurant.api.domain.person.PersonRepository;
import com.restaurant.api.domain.person.dto.CreatePersonDTO;
import com.restaurant.api.domain.person.dto.PersonDetailsDTO;
import com.restaurant.api.domain.person.useCases.CreatePersonUseCase;
import jakarta.persistence.EntityNotFoundException;
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

    @Autowired
    private CreatePersonUseCase createPersonUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity create(@Valid @RequestBody CreatePersonDTO data, UriComponentsBuilder uriComponent){
        var person = createPersonUseCase.execute(data);

        var uri = uriComponent.path("/person/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonDetailsDTO(person));
    }

    @GetMapping
    public ResponseEntity listAll(){
        var result = repository.findAll().stream()
                .map(tableOrderEntity -> {
                    var dto = new PersonDetailsDTO(tableOrderEntity);
                    return dto;
                });
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable UUID id){
        var result = repository.findById(id);
        if(result.isEmpty()){
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(new PersonDetailsDTO(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
