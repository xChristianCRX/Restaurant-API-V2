package com.restaurant.api.controller;

import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.domain.menu.dto.CreateMenuItemDTO;
import com.restaurant.api.domain.menu.dto.MenuItemDetailsDTO;
import com.restaurant.api.domain.menu.useCases.CreateMenuItemUseCase;
import com.restaurant.api.domain.menu.useCases.UpdateMenuItemUseCase;
import com.restaurant.api.domain.menu.useCases.DeleteMenuItemUseCase;
import com.restaurant.api.domain.menu.dto.UpdateMenuItemDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuItemRepository repository;
    private final CreateMenuItemUseCase createMenuItemUseCase;
    private final UpdateMenuItemUseCase updateMenuItemUseCase;
    private final DeleteMenuItemUseCase deleteMenuItemUseCase;

    public MenuController(MenuItemRepository repository, CreateMenuItemUseCase createMenuItemUseCase, UpdateMenuItemUseCase updateMenuItemUseCase, DeleteMenuItemUseCase deleteMenuItemUseCase) {
        this.repository = repository;
        this.createMenuItemUseCase = createMenuItemUseCase;
        this.updateMenuItemUseCase = updateMenuItemUseCase;
        this.deleteMenuItemUseCase = deleteMenuItemUseCase;
    }

    @PutMapping
    public ResponseEntity<MenuItemEntity> updateItem(@Valid @RequestBody UpdateMenuItemDTO data) {
        var item = updateMenuItemUseCase.execute(data);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<MenuItemEntity> createItem(@Valid @RequestBody CreateMenuItemDTO data, UriComponentsBuilder uriComponent){
        var item = createMenuItemUseCase.execute(data);
        var uri = uriComponent.path("/menu/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(item);
    }

    @GetMapping
    public ResponseEntity<List<MenuItemDetailsDTO>> listAll(){
        var result = repository.findAll().stream()
            .map(item -> new MenuItemDetailsDTO(
                    item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getType()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        deleteMenuItemUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
