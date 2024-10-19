package com.springboot.demo.controller;


import com.springboot.demo.model.Users;
import com.springboot.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Users user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        userService.signup(user);
        return ResponseEntity.ok("User signed up successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        Optional<Users> user = userService.findByEmail(email);
        System.out.println("USER : " + user.get().getPassword());
        System.out.println("password "  + password);
        if (user.isPresent() && Objects.equals(password, user.get().getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        if (userService.findByEmail(email).isPresent()) {
            userService.resetPassword(email, newPassword);
            return ResponseEntity.ok("Password reset successfully");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
