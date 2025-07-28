package com.restaurant.api.domain.menu.useCases;

import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.domain.menu.dto.UpdateMenuItemDTO;
import com.restaurant.api.infra.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateMenuItemUseCase {
    private final MenuItemRepository repository;

    public UpdateMenuItemUseCase(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItemEntity execute(UpdateMenuItemDTO data) {
        System.out.println(data);
        var item = repository.findById(data.id())
                .orElseThrow(() -> new NotFoundException("Item não encontrado"));
        item.setName(data.name());
        item.setDescription(data.description());
        item.setPrice(data.price());
        item.setType(data.type());
        return repository.save(item);
    }
}
