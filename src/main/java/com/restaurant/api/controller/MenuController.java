package com.restaurant.api.controller;

import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.domain.menu.dto.CreateMenuItemDTO;
import com.restaurant.api.domain.menu.useCases.CreateMenuItemUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuItemRepository repository;

    @Autowired
    private CreateMenuItemUseCase createMenuItemUseCase;

    @PostMapping
    public ResponseEntity createItem(@Valid @RequestBody CreateMenuItemDTO data, UriComponentsBuilder uriComponent){
        var item = createMenuItemUseCase.execute(data);
        repository.save(item);

        var uri = uriComponent.path("/menu/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(item);
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
