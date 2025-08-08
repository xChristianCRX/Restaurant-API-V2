package com.restaurant.api.domain.addition.useCases;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.addition.AdditionRepository;
import com.restaurant.api.domain.addition.dto.CreateAdditionDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public class CreateAdditionUseCase {
    private final AdditionRepository additionRepository;

    public CreateAdditionUseCase(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }

    public AdditionEntity execute(CreateAdditionDTO data) {
        additionRepository.findByName(data.name())
                .ifPresent(additionEntity -> {
                    throw new AlreadyExistException("This addition already exists!");
                });

        return additionRepository.save(new AdditionEntity(data));
    }
}
