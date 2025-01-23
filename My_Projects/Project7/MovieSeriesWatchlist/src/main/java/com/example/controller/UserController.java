package com.example.controller;

import com.example.dto.SignupRequest;
import com.example.dto.LoginRequest;
import com.example.model.User;
import com.example.service.UserService;
import com.example.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername()); // Set username
        user.setEmail(signupRequest.getEmail());       // Set email
        user.setPassword(signupRequest.getPassword()); // Set password
        userService.saveUser(user); // Save the user
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully! Please log in.");
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(authentication); // Generate JWT
        return ResponseEntity.ok(token);
    }

    // Delete User account endpoint
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser() {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUserByEmail(currentEmail);
        return ResponseEntity.ok("User account deleted successfully!");
    }
}