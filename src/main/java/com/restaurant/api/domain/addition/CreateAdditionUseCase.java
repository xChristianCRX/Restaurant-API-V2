package com.restaurant.api.domain.addition;

import com.restaurant.api.domain.addition.dto.CreateAdditionDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAdditionUseCase {

    @Autowired
    private AdditionRepository additionRepository;

    public AdditionEntity execute(CreateAdditionDTO data) {
        this.additionRepository.findByName(data.name())
                .ifPresent(additionEntity -> {
                    throw new AlreadyExistException("This addition already exists!");
                });

        return new AdditionEntity(data);
    }
}
