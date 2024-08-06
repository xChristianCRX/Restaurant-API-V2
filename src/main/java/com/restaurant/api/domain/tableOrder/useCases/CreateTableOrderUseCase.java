package com.restaurant.api.domain.tableOrder.useCases;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.addition.AdditionRepository;
import com.restaurant.api.domain.menu.MenuItemEntity;
import com.restaurant.api.domain.menu.MenuItemRepository;
import com.restaurant.api.domain.person.PersonEntity;
import com.restaurant.api.domain.person.PersonRepository;
import com.restaurant.api.domain.tableOrder.TableOrderEntity;
import com.restaurant.api.domain.tableOrder.TableOrderRepository;
import com.restaurant.api.domain.tableOrder.dto.CreateTableOrderDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTableOrderUseCase {

    @Autowired
    private TableOrderRepository tableOrderRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private AdditionRepository additionRepository;

    public TableOrderEntity execute(CreateTableOrderDTO data){
        var waiter = personRepository.findById(data.waiter().getId())
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
