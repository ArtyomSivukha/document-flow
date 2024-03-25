package com.example.document_flow.controller;

import com.example.document_flow.service.NotificationService;
import com.example.document_flow.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping
    private ResponseEntity<?> getAll(){
        return new ResponseEntity<>(notificationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/my")
    private ResponseEntity<?> getAllMy(){
        return new ResponseEntity<>(CurrentUser.getInstance().getUser().getNotifications(), HttpStatus.OK);
    }

}
