package com.example.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class LoginRequest {
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
//public class LoginRequest {
//    @NotBlank
//    private String email;
//
//    @NotBlank
//    private String password;
//}
