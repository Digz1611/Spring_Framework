package com.example.controller;

import com.example.dto.SignupRequest;
import com.example.dto.LoginRequest;
import com.example.dto.UserResetRequest;
import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername()); // Set username
        user.setEmail(signupRequest.getEmail());       // Set email
        user.setPassword(signupRequest.getPassword()); // Set password
        userService.registerUser(user); // Register the user
        return ResponseEntity.ok("User registered successfully! Please log in.");
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), // Use email for login
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication); // Set authentication in the context
        return ResponseEntity.ok("Login successful!");
    }

    // Update User details endpoint
    @PutMapping("/update")
    public ResponseEntity<String> updateUserDetails(@RequestBody UserResetRequest updatedUserRequest) {
        // Get the current authenticated user's username
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // Pass only the relevant fields to the service method
        User updatedUser = new User();
        updatedUser.setUsername(updatedUserRequest.getUsername());
        updatedUser.setEmail(updatedUserRequest.getEmail());
        updatedUser.setPassword(updatedUserRequest.getPassword());

        userService.updateUserDetails(currentUsername, updatedUser); // Use username for update
        return ResponseEntity.ok("User details updated successfully!");
    }

    // Delete User account endpoint
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser() {
        // Get the current authenticated user's username
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUserByEmail(currentUsername); // Delete user by username
        return ResponseEntity.ok("User account deleted successfully!");
    }
}
