package com.example.taskmanager.controller;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class AuthController
{
    private final UserService userService;
    public AuthController(UserService userService)
    {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user)
    {
        user.setRole("ROLE_USER"); //Every user get default role USER
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
