package com.lms2025.librarymanagementsystem.service;

import com.lms2025.librarymanagementsystem.model.user;
import com.lms2025.librarymanagementsystem.repository.userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private userrepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public user registerUser(user user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public user findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public boolean validateUser(String username, String password) {
        user user = userRepository.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}