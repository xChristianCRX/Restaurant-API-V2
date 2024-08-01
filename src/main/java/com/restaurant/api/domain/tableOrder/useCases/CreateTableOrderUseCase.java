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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
        var waiter = personRepository.findById(data.waiter().getId()).orElse(new PersonEntity());
        var item = menuItemRepository.findById(data.item().getId()).orElse(new MenuItemEntity());
        var additions = data.additions().stream()
                .map(additionEntity -> additionRepository.findById(additionEntity.getId()).orElse(new AdditionEntity()))
                .collect(Collectors.toList());

        return new TableOrderEntity(item, waiter, additions, data.observations());
    }
}
