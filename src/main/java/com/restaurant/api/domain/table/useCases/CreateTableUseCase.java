package com.restaurant.api.domain.table.useCases;

import com.restaurant.api.domain.table.TableEntity;
import com.restaurant.api.domain.table.TableRepository;
import com.restaurant.api.domain.table.dto.CreateTableDTO;
import com.restaurant.api.infra.exceptions.AlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public class CreateTableUseCase {
    private final TableRepository repository;

    public CreateTableUseCase(TableRepository tableRepository) {
        this.repository = tableRepository;
    }

    public TableEntity execute(CreateTableDTO dto) {
        repository.findById(dto.number())
                .ifPresent(existing -> {
                    throw new AlreadyExistException("Table with number " + dto.number() + " already exists!");
                });

        return repository.save(new TableEntity(dto.number()));
    }
}
