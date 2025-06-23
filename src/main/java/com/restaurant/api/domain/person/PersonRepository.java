package com.restaurant.api.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, UUID>{
    Optional<PersonEntity> findByUsernameOrEmail(String username, String email);
    Optional<PersonEntity> findByUsername(String username);
}
