package com.restaurant.api.controller;

import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.domain.menu.dto.CreateMenuItemDTO;
import com.restaurant.api.domain.menu.useCases.CreateMenuItemUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuItemRepository repository;
    private final CreateMenuItemUseCase createMenuItemUseCase;

    public MenuController(MenuItemRepository repository, CreateMenuItemUseCase createMenuItemUseCase) {
        this.repository = repository;
        this.createMenuItemUseCase = createMenuItemUseCase;
    }

    @PostMapping
    public ResponseEntity<MenuItemEntity> createItem(@Valid @RequestBody CreateMenuItemDTO data, UriComponentsBuilder uriComponent){
        var item = createMenuItemUseCase.execute(data);
        var uri = uriComponent.path("/menu/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(item);
    }

    @GetMapping
    public ResponseEntity<List<MenuItemEntity>> listAll(){
        var result = repository.findAll();
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MenuItemEntity> delete(@PathVariable UUID id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
