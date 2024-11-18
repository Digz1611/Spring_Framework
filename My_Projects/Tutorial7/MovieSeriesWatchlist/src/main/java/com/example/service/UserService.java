package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user with password hashing
    public User registerUser(User user) {
        // Check if the email already exists in the database
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        // Check if the username already exists in the database
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        // Hash the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user); // Save the user to the database with the hashed password
    }

    // Update user details (email, username, password)
    public void updateUserDetails(String email, User updatedUser) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        // Update email and username
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());

        // Hash the password if it is updated
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        userRepository.save(user); // Save updated user details
    }

    // Delete user account
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user); // Deletes the user from the database
    }

    // Find user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
