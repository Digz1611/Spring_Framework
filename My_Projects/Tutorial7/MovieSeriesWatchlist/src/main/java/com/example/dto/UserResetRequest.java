package com.example.dto;

import lombok.Data;

@Data
public class UserResetRequest {
    private String username; // Added username

    private String email;

    private String password;

    // Getters and Setters (Lombok handles them, but you can include them if needed)
}
