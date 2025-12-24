package com.example.docservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.docservice.model.Doc;

public interface DocumentRepository extends MongoRepository<Doc, Long> {
    List<Doc> findBySoaId(String soaId);
    List<Doc> findByControlId(String controlId);
}

