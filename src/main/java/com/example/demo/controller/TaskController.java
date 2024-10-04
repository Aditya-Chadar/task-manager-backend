package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

import java.util.List;

@RestController // Added this annotation
@RequestMapping("/api/tasks") // Set the base path for all endpoints
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Endpoint to fetch all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Endpoint to add a new task
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task newTask) {
        Task createdTask = taskService.addTask(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    // Endpoint to fetch a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to update a task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDetails));
    }

    // Endpoint to delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
