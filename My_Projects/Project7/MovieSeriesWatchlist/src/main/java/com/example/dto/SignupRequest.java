package com.example.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class SignupRequest {
    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;
}



//package com.example.dto;
//
//import lombok.Data;
//import jakarta.validation.constraints.NotBlank;
//
//@Data
//public class SignupRequest {
//    @NotBlank
//    private String username; // Changed to username from email
//
//    @NotBlank
//    private String email;
//
//    @NotBlank
//    private String password;
//}
