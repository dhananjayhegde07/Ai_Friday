package com.ai.friday.repository;

import com.ai.friday.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,String> {

        Optional<UserEntity> findByEmail(String email);

        boolean existsByEmail(String email);
}
