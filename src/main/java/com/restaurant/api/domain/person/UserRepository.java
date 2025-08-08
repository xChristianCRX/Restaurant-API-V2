package com.restaurant.api.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
    Optional<UserEntity> findByUsername(String username);
}
