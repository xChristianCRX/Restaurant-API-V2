package com.restaurant.api.domain.menu.useCases;

import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.infra.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteMenuItemUseCase {
    private final MenuItemRepository repository;

    public DeleteMenuItemUseCase(MenuItemRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Item não encontrado");
        }
        repository.deleteById(id);
    }
}
