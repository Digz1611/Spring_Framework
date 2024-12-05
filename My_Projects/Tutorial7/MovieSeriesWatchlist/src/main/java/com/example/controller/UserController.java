package com.example.controller;

import com.example.dto.SignupRequest;
import com.example.dto.LoginRequest;
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
        userService.saveUser(user); // Save the user
        return ResponseEntity.ok("User registered successfully! Please log in.");
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Authenticate using email and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), // Use email for login
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Login successful!");
    }

    // Delete User account endpoint
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser() {
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUserByEmail(currentEmail);
        return ResponseEntity.ok("User account deleted successfully!");
    }
}




//package com.example.controller;
//
//import com.example.dto.SignupRequest;
//import com.example.dto.LoginRequest;
//import com.example.model.User;
//import com.example.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    // Signup endpoint
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
//        User user = new User();
//        user.setUsername(signupRequest.getUsername()); // Set username
//        user.setEmail(signupRequest.getEmail());       // Set email
//        user.setPassword(signupRequest.getPassword()); // Set password
//        userService.saveUser(user); // Save the user
//        return ResponseEntity.ok("User registered successfully! Please log in.");
//    }
//
//    // Login endpoint
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        // Authenticate using email and password
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getEmail(), // Use email for login
//                        loginRequest.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return ResponseEntity.ok("Login successful!");
//    }
//
//    // Delete User account endpoint
//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteUser() {
//        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        userService.deleteUserByEmail(currentEmail);
//        return ResponseEntity.ok("User account deleted successfully!");
//    }
//}
