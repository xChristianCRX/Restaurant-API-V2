package com.restaurant.api.domain.menu.useCases;

import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.domain.menu.dto.CreateMenuItemDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMenuItemUseCase {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItemEntity execute(CreateMenuItemDTO data){
        this.menuItemRepository.findByName(data.name())
                .ifPresent(menuItem -> {
                    throw new AlreadyExistException("This item name already exists!");
                });

        return new MenuItemEntity(data);
    }
}
