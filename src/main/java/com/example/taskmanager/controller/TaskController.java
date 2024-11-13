package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//Indicates that the class handles HTTP requests.
@RequestMapping("/tasks") //All endpoints in this class start with /tasks
public class TaskController
{
    private final TaskRepository taskRepository;
    @Autowired //We inject the repository using the @Autowired annotation
    public TaskController(TaskRepository taskRepository)
    {
        this.taskRepository = taskRepository;
    }
    @GetMapping //The method supports GET requests.
    public List<Task> getTasks()
    {
        return taskRepository.findAll();
    }
    @PostMapping //The method supports POST requests.
    public Task addTask(@RequestBody Task task) //Data sent in the request is automatically mapped to a Task object thanks to @RequestBody.
    {
        return taskRepository.save(task);
    }
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id)
    {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task with id " +id+ "not found"));
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updateTask)
    {
        return taskRepository.findById(id)
                .map(task ->{
                    task.setName(updateTask.getName());
                    task.setDescription(updateTask.getDescription());
                    task.setCompleted(updateTask.isCompleted());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new IllegalArgumentException("Task with id " +id+ "not found"));
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id)
    {
        if (!taskRepository.existsById(id))
        {
            throw new IllegalArgumentException("Task with id " +id+ "not found");
        }
        taskRepository.deleteById(id);
    }
}
