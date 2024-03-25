package com.example.document_flow.controller;

import com.example.document_flow.entity.Document;
import com.example.document_flow.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        return new ResponseEntity<>(documentService.saveDocument(file), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(documentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(documentService.newUpload(file), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDocument(@PathVariable Long id) {
        Document document = documentService.getDocument(id)
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + id));
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        Document updatedDocument = documentService.updateDocument(id, file);
        return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long id) throws IOException {
     return  new ResponseEntity<>(documentService.deleteById(id), HttpStatus.OK);
    }
}