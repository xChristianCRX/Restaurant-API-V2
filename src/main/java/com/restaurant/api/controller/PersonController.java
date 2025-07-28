package com.restaurant.api.controller;

import com.restaurant.api.domain.person.PersonRepository;
import com.restaurant.api.domain.person.dto.CreatePersonDTO;
import com.restaurant.api.domain.person.dto.PersonDetailsDTO;
import com.restaurant.api.domain.person.useCases.CreatePersonUseCase;
import com.restaurant.api.domain.person.useCases.UpdatePersonUseCase;
import com.restaurant.api.domain.person.useCases.DeletePersonUseCase;
import com.restaurant.api.domain.person.dto.UpdatePersonDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RequestMapping("/person")
@Validated
@RestController
public class PersonController {
    private final PersonRepository repository;
    private final CreatePersonUseCase createPersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final DeletePersonUseCase deletePersonUseCase;

    public PersonController(PersonRepository repository, CreatePersonUseCase createPersonUseCase, UpdatePersonUseCase updatePersonUseCase, DeletePersonUseCase deletePersonUseCase) {
        this.repository = repository;
        this.createPersonUseCase = createPersonUseCase;
        this.updatePersonUseCase = updatePersonUseCase;
        this.deletePersonUseCase = deletePersonUseCase;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PersonDetailsDTO> create(@Valid @RequestBody CreatePersonDTO data, UriComponentsBuilder uriComponent){
        var person = createPersonUseCase.execute(data);
        var uri = uriComponent.path("/person/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonDetailsDTO(person));
    }

    @GetMapping
    public ResponseEntity<List<PersonDetailsDTO>> listAll(){
        var result = repository.findAll().stream()
                .map(PersonDetailsDTO::new).toList();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDetailsDTO> findById(@PathVariable UUID id){
        var result = repository.findById(id);
        if(result.isEmpty()){
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().body(new PersonDetailsDTO(result));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PersonDetailsDTO> update(@Valid @RequestBody UpdatePersonDTO data) {
        var person = updatePersonUseCase.execute(data);
        return ResponseEntity.ok().body(new PersonDetailsDTO(person));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deletePersonUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
