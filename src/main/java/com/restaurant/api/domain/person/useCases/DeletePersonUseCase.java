package com.restaurant.api.domain.person.useCases;

import com.restaurant.api.domain.person.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeletePersonUseCase {
    private final PersonRepository personRepository;

    public DeletePersonUseCase(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void execute(UUID id) {
        var person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        personRepository.deleteById(id);
    }
}
