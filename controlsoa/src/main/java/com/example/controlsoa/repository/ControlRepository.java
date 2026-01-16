package com.example.controlsoa.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.controlsoa.model.Control;

public interface ControlRepository extends MongoRepository<Control, String> {
    Optional<Control> findTopByOrderByIdDesc();
    Control findById(int id);
}
