package com.restaurant.api.controller;

import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.domain.menu.dto.CreateMenuItemDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuItemRepository repository;

    @PostMapping
    public ResponseEntity createItem(@Valid @RequestBody CreateMenuItemDTO data, UriComponentsBuilder uriComponent){
        var item = new MenuItemEntity(data);
        repository.save(item);

        var uri = uriComponent.path("/menu/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(item);
    }
}
