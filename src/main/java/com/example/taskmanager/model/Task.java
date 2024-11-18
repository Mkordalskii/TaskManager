package com.example.taskmanager.model;

import jakarta.persistence.*;

@Entity // Indicates that this class is a JPA entity.
@Table(name = "tasks") // Maps this class to the "tasks" table in the database.
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key value.
    private Long id;

    private String name;
    private String description;
    private boolean completed;

    // Default constructor (required by JPA).
    public Task() {}

    // Getters and setters for all fields.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
