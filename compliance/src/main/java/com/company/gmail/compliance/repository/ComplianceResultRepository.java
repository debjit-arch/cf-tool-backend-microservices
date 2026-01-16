package com.company.gmail.compliance.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.company.gmail.compliance.model.ComplianceResult;

public interface ComplianceResultRepository extends MongoRepository<ComplianceResult, String> {
}
