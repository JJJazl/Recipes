package com.example.recipes.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.recipes.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailIgnoreCase(String email);
}
