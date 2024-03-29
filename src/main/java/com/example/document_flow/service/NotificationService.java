package com.example.document_flow.service;

import com.example.document_flow.entity.Notification;
import com.example.document_flow.repository.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements Getable<Notification> {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getOne(Long id) {
        return notificationRepository.findById(id).get();
    }

    public Notification addOne(Notification notification){
        return notificationRepository.save(notification);
    }

}
