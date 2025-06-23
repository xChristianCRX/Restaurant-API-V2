package com.restaurant.api.domain.menu.useCases;

import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.domain.menu.dto.CreateMenuItemDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMenuItemUseCase {
    private final MenuItemRepository repository;

    public CreateMenuItemUseCase(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItemEntity execute(CreateMenuItemDTO data){
        repository.findByName(data.name())
                .ifPresent(menuItem -> {
                    throw new AlreadyExistException("This product already exists!");
                });

        return repository.save(new MenuItemEntity(data));
    }
}
