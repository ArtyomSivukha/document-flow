package com.example.document_flow.service;

import com.example.document_flow.entity.Project;
import com.example.document_flow.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements Getable<Project> {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project getOne(Long id) {
        return projectRepository.findById(id).get();
    }

    public Project addOne(Project project) {
        return projectRepository.save(project);
    }
}
