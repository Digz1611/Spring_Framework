package com.example.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class SignupRequest {
    @NotBlank
    private String username; // Changed to username from email

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
