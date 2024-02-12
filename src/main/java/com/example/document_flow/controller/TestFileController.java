package com.example.document_flow.controller;

import com.example.document_flow.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class TestFileController {
    private final StorageService storageService;

    public TestFileController(StorageService storageService) {
        this.storageService = storageService;
    }
}
