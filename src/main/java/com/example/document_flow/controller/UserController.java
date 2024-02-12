package com.example.document_flow.controller;


import com.example.document_flow.entity.User;
import com.example.document_flow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getOne(id), HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/addOne")
    public ResponseEntity<?> addOne(@RequestBody User user) {
        return new ResponseEntity<>(userService.addOne(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteOne(id), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editOne(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.editOne(id, user), HttpStatus.OK);
    }
}
