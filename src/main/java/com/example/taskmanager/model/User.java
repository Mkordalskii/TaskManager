package com.example.taskmanager.model;

import jakarta.persistence.*;

@Entity // Indicates this class is a JPA entity mapped to a database table.
@Table(name = "users") // Specifies the name of the database table.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique IDs for each user.
    private Long id;

    private String username; // The username of the user.
    private String password; // The encoded password of the user.
    private String role; // The role of the user (e.g., ROLE_USER, ROLE_ADMIN).

    // Default constructor required by JPA.
    public User() {}

    // Getters and setters for the fields.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
