package com.example.taskmanager.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indicates that this class handles HTTP requests.
@RequestMapping("/admin") // All endpoints in this class will start with /admin.
public class AdminController {

    /**
     * Provides access to the admin dashboard.
     * Accessible only by users with the 'ADMIN' role.
     * @return A welcome message for admins.
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Restricts access to users with the 'ADMIN' role.
    public String adminAccess() {
        return "Welcome, Admin!";
    }
}
