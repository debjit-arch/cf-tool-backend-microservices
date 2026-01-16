package com.example.controlsoa.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.controlsoa.model.Soa;

public interface SoaRepository extends MongoRepository<Soa, String> {
    List<Soa> findByControlId(int controlId);
    Soa findById(long id);
}
