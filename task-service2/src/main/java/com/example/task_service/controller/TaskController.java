package com.example.task_service.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.task_service.model.Task;
import com.example.task_service.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Generate sequential Task ID (T-1, T-2, …)
    private String generateTaskId() {
        List<Task> tasks = taskRepository.findAll();
        int lastNum = tasks.stream()
                .map(t -> t.getTaskId())
                .filter(id -> id != null && id.startsWith("T-"))
                .mapToInt(id -> Integer.parseInt(id.split("-")[1]))
                .max()
                .orElse(0);
        return "T-" + (lastNum + 1);
    }

    // GET all tasks with optional filters
    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false) String department,
                                  @RequestParam(required = false) String status) {
        List<Task> tasks = taskRepository.findAll();
        if (department != null) {
            tasks.removeIf(t -> !t.getDepartment().equals(department));
        }
        if (status != null) {
            tasks.removeIf(t -> !t.getStatus().equalsIgnoreCase(status));
        }
        return tasks;
    }
    @GetMapping("/hello")
    public String Helloworld() {
    	return "Hello World Task Service";
    }

    // GET task by taskId
    @GetMapping("/{taskId}")
    public Task getTaskByTaskId(@PathVariable String taskId) {
        return taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // POST create or update task
    @PostMapping
    public Task createOrUpdateTask(@RequestBody Task task) {
        task.setUpdatedAt(new Date());
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(new Date());
        }

        if (task.getTaskId() == null || task.getTaskId().isEmpty()) {
            task.setTaskId(generateTaskId());
        }

        Optional<Task> existing = taskRepository.findByTaskId(task.getTaskId());
        if (existing.isPresent()) {
            task.setId(existing.get().getId());
        }

        return taskRepository.save(task);
    }

    // PUT update task
    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable String taskId, @RequestBody Task taskDetails) {
        Task task = taskRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setDepartment(taskDetails.getDepartment() != null ? taskDetails.getDepartment() : task.getDepartment());
        task.setEmployee(taskDetails.getEmployee() != null ? taskDetails.getEmployee() : task.getEmployee());
        task.setDescription(taskDetails.getDescription() != null ? taskDetails.getDescription() : task.getDescription());
        task.setStartDate(taskDetails.getStartDate() != null ? taskDetails.getStartDate() : task.getStartDate());
        task.setEndDate(taskDetails.getEndDate() != null ? taskDetails.getEndDate() : task.getEndDate());
        task.setStatus(taskDetails.getStatus() != null ? taskDetails.getStatus() : task.getStatus());
        task.setUpdatedAt(new Date());

        return taskRepository.save(task);
    }

    // DELETE task
    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId) {
        Optional<Task> existing = taskRepository.findByTaskId(taskId);
        if (!existing.isPresent()) return "Task not found";

        taskRepository.delete(existing.get());
        return "Task deleted successfully";
    }
}