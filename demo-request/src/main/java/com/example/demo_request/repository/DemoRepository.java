package com.example.demo_request.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo_request.model.Demos;

public interface DemoRepository extends MongoRepository<Demos, String> {

    Optional<Demos> findByOrganization(String organization);
}
