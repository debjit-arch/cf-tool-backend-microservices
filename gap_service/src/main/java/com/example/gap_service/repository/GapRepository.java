package com.example.gap_service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.gap_service.model.Gaps;

public interface GapRepository extends MongoRepository<Gaps, String> {

    Optional<Gaps> findByClauseAndQuestion(String clause, String question);

    Optional<Gaps> findByDocumentURL(String url);

    Optional<Gaps> findByPracticeEvidence(String url);
}
