package com.example.document_flow.service;

import com.example.document_flow.entity.Document;
import com.example.document_flow.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(MultipartFile file) throws IOException {
        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
        document.setContent(file.getBytes());
        return documentRepository.save(document);
    }

    public Optional<Document> getDocument(Long id) {
        return documentRepository.findById(id);
    }

    public Document updateDocument(Long id, MultipartFile file) throws IOException {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + id));
        document.setName(file.getOriginalFilename());
        document.setType(file.getContentType());
        document.setContent(file.getBytes());
        return documentRepository.save(document);
    }
}
