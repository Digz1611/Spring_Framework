package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // Find user by email

    Optional<User> findByUsername(String username); // Find user by username

    boolean existsByEmail(String email); // Check if email exists

    boolean existsByUsername(String username); // Check if username exists
}
