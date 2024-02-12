package com.example.document_flow.repository;

import com.example.document_flow.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<Document, Long> {

    Optional<Document> findByName(String filename);

}
