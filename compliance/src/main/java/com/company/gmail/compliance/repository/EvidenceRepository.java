package com.company.gmail.compliance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.company.gmail.compliance.model.Evidence;

public interface EvidenceRepository extends MongoRepository<Evidence, String> {
}
