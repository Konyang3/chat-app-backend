package com.example.chatapp.controller;

import com.example.chatapp.entity.User;
import com.example.chatapp.repository.UserRepository;
import com.example.chatapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User foundUser = userRepository.findByUsername(user.getUsername())
                .orElse(null);

        if (foundUser == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(foundUser.getUsername());
        return ResponseEntity.ok(token);
    }
}
