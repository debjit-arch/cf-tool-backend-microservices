package com.example.task_service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.task_service.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {
    Optional<Task> findByTaskId(String taskId);
    void deleteByTaskId(String taskId);
}