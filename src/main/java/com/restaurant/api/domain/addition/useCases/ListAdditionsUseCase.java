package com.restaurant.api.domain.addition.useCases;

import com.restaurant.api.domain.addition.AdditionEntity;
import com.restaurant.api.domain.addition.AdditionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ListAdditionsUseCase {

    private final AdditionRepository additionRepository;

    public ListAdditionsUseCase(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }

    public Page<AdditionEntity> execute(String name, Pageable pageable) {
        return additionRepository.findByFilters(name, pageable);
    }
}
