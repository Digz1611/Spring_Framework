//package com.example.service;
//
//import com.example.model.User;
//import com.example.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
//        // Allow login by either email or username
//        User user = userRepository.findByEmail(identifier)
//                .or(() -> userRepository.findByUsername(identifier)) // Search by username if not found by email
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email or username: " + identifier));
//
//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(user.getUsername()) // Use username for Spring Security
//                .password(user.getPassword()) // Set password for authentication
//                .build();
//    }
//}
//
package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch the user by email and throw exception if not found
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Return Spring Security User object with necessary credentials
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())  // Email is treated as the username
                .password(user.getPassword()) // Password for authentication
                .build();
    }
}
