package com.example.chatapp.controller;

import com.example.chatapp.dto.RegisterRequest;
import com.example.chatapp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        userService.register(request.getUsername(), request.getPassword());
        return "User registered successfully!";
    }
}
