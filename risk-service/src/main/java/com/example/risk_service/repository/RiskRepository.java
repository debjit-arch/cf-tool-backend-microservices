package com.example.risk_service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.risk_service.model.Risk;

public interface RiskRepository extends MongoRepository<Risk, String> {
    Optional<Risk> findByRiskId(String riskId);
    void deleteByRiskId(String riskId);
}
