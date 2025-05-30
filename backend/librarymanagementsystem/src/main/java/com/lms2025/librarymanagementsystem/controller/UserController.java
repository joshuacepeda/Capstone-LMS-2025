package com.lms2025.librarymanagementsystem.controller;

import com.lms2025.librarymanagementsystem.model.user;
import com.lms2025.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<user> registerUser(@RequestBody user user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody user user) {
        boolean isValid = userService.validateUser(user.getUsername(), user.getPassword());
        if (isValid) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<user> getUserByUsername(@PathVariable String username) {
        user user = userService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}