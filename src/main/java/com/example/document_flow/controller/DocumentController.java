package com.example.document_flow.controller;

import com.example.document_flow.entity.Document;
import com.example.document_flow.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<Document> uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {
        Document document = documentService.saveDocument(file);
        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable Long id) {
        Document document = documentService.getDocument(id)
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + id));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(document.getContent());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        Document updatedDocument = documentService.updateDocument(id, file);
        return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
    }
}