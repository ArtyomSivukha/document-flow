package com.example.document_flow.controller;

import com.example.document_flow.entity.Document;
import com.example.document_flow.entity.enums.DocumentStatus;
import com.example.document_flow.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        Document document = documentService.getDocumentById(id);

        try {
            Path filePath = Paths.get(document.getFilePath()).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() && resource.isReadable()) {
                String contentType = "application/octet-stream";
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ошибка при попытке скачать файл: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        Document document = documentService.getDocument(id)
                .orElseThrow(() -> new IllegalArgumentException("Документ с : " + id + " не найден"));
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(documentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<?> getFromCurrentUser(){
        return new ResponseEntity<>(documentService.getFromCurrentUser(), HttpStatus.OK);
    }

    @GetMapping("/myShared")
    public ResponseEntity<?> getSharedFromCurrentUser(){
        return new ResponseEntity<>(documentService.getShared(),HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(documentService.newUpload(file), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateOne(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(documentService.updateDocument(id, file), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) throws IOException {
     return  new ResponseEntity<>(documentService.deleteById(id), HttpStatus.OK);
    }

    @PostMapping("/share")
    public ResponseEntity<?> share(@RequestParam Long documentId, @RequestParam String userLogin ) {
        return new ResponseEntity<>(documentService.share(documentId, userLogin), HttpStatus.OK);
    }
    @PostMapping("/confirm/{id}")
    public ResponseEntity<?> confirm(@PathVariable Long id, @RequestParam DocumentStatus documentStatus) {
        return new ResponseEntity<>(documentService.confirm(id, documentStatus), HttpStatus.OK);
    }

}