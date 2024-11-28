package com.example.chatapp.repository;

import com.example.chatapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String email);
}