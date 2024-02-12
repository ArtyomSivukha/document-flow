package com.example.document_flow.controller;

import com.example.document_flow.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping
    public ResponseEntity<?> showMainPAge() {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
