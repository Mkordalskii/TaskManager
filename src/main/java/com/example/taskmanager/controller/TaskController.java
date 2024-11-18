package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController // Indicates that this class handles HTTP requests.
@RequestMapping("/tasks") // All endpoints in this class will start with /tasks.
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Retrieves all tasks from the database.
     * Accessible by users with the roles 'USER' or 'ADMIN'.
     * @return List of tasks.
     */
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // Restricts access to users with 'USER' or 'ADMIN' roles.
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    /**
     * Adds a new task to the database.
     * Accessible only by users with the 'ADMIN' role.
     * @param task Task data provided in the request body.
     * @return The created task with status 201 Created.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.status(201).body(savedTask);
    }

    /**
     * Retrieves a task by its ID.
     * @param id The ID of the task.
     * @return The task if found, otherwise 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing task by its ID.
     * Accessible only by users with the 'ADMIN' role.
     * @param id The ID of the task to update.
     * @param updateTask The updated task data.
     * @return The updated task or 404 Not Found if the task doesn't exist.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task updateTask) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(updateTask.getName());
                    task.setDescription(updateTask.getDescription());
                    task.setCompleted(updateTask.isCompleted());
                    return ResponseEntity.ok(taskRepository.save(task));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a task by its ID.
     * Accessible only by users with the 'ADMIN' role.
     * @param id The ID of the task to delete.
     * @return 204 No Content if successful or 404 Not Found if the task doesn't exist.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves tasks with optional filtering by completion status and pagination.
     * @param completed Filter by completion status (default: false).
     * @param page The page number (default: 0).
     * @param size The number of tasks per page (default: 10).
     * @return A paginated list of tasks filtered by completion status.
     */
    @GetMapping("/filter")
    public Page<Task> getTasksWithFilter(
            @RequestParam(required = false, defaultValue = "false") boolean completed,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findByCompleted(completed, pageable);
    }
}
