package com.medtech.patientportal.service;

import com.medtech.patientportal.entity.User;
import com.medtech.patientportal.repository.UserRepository;
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

    public User registerUser(User user) {
        // Password encoding is now done in AuthController
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    // Add this method for login
    public Optional<User> findByEmailOrPhoneNumber(String username) {
        return userRepository.findByEmailOrPhoneNumber(username);
    }
}