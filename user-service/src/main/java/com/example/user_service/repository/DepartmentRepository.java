package com.example.user_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.user_service.model.Department;

public interface DepartmentRepository extends MongoRepository<Department, String> {
    boolean existsByName(String name);
}
