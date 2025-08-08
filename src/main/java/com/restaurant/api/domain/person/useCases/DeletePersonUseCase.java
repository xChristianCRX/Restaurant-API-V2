package com.restaurant.api.domain.person.useCases;

import com.restaurant.api.domain.person.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeletePersonUseCase {
    private final UserRepository userRepository;

    public DeletePersonUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void execute(UUID id) {
        var person = userRepository.findById(id);
        if (person.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}
