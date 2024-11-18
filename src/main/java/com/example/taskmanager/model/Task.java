package com.example.taskmanager.model;

import jakarta.persistence.*;

@Entity // Indicates this class is a JPA entity mapped to a database table.
@Table(name = "tasks") // Specifies the name of the database table.
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique IDs for each task.
    private Long id;

    private String name; // The name of the task.
    private String description; // A brief description of the task.
    private boolean completed; // Indicates whether the task is completed.

    // Default constructor required by JPA.
    public Task() {}

    // Getters and setters for the fields.
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
