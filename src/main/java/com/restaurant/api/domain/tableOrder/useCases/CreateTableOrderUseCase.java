package com.restaurant.api.domain.tableOrder.useCases;

import com.restaurant.api.domain.addition.AdditionRepository;
import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.domain.person.UserRepository;
import com.restaurant.api.domain.tableOrder.TableOrderEntity;
import com.restaurant.api.domain.tableOrder.TableOrderRepository;
import com.restaurant.api.domain.tableOrder.dto.CreateTableOrderDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CreateTableOrderUseCase {
    private final TableOrderRepository tableOrderRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final AdditionRepository additionRepository;

    public CreateTableOrderUseCase(TableOrderRepository tableOrderRepository, UserRepository userRepository,
                                   MenuItemRepository menuItemRepository, AdditionRepository additionRepository) {
        this.tableOrderRepository = tableOrderRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
        this.additionRepository = additionRepository;
    }

    public TableOrderEntity execute(CreateTableOrderDTO data){
        var waiter = userRepository.findById(data.waiter().getId())
                .orElseThrow(() -> new EntityNotFoundException("Waiter ID not found!"));
        var item = menuItemRepository.findById(data.item().getId())
                .orElseThrow(() -> new EntityNotFoundException("Item ID not found!"));
        var additions = data.additions().stream()
                .map(additionEntity -> additionRepository.findById(additionEntity.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Addition ID not found!")))
                .toList();

        var tableOrderEntity = new TableOrderEntity(item, waiter, additions, data.observations());
        tableOrderRepository.save(tableOrderEntity);
        return tableOrderEntity;
    }
}
