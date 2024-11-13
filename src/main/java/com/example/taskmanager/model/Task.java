package com.example.taskmanager.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity //annotation tells that class will be as mapped as label, and every obj of this class will be a row in the label
public class Task {
    @Id//tells that attribute id is a primary key of label
    @GeneratedValue(strategy=GenerationType.IDENTITY) //automatically generates field value(1,2,3...)
    private Long id; //Uniqe task ID

    @NotBlank(message = "Name cannot be blank")
    private String name; //Task name
    private String description; //Task description
    private boolean completed; //Is task completed or not

    //constructors (default and with parameters)
    public Task() {} //default constructor required by JPA(Java Persistence API Hibernate)
    public Task(String name, String description,boolean completed)
    {
        this.name = name;
        this.description = description;
        this.completed = completed;
    }
    //Getters and setters
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public boolean isCompleted()
    {
        return completed;
    }
    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }
}
