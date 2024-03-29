package com.example.document_flow.service;

import com.example.document_flow.entity.Report;
import com.example.document_flow.entity.Task;
import com.example.document_flow.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements Getable<Task> {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getOne(Long id) {
        return taskRepository.findById(id).get();
    }

    public Task addOne(Task task){
        return taskRepository.save(task);
    }
}
