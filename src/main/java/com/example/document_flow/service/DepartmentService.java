package com.example.document_flow.service;

import com.example.document_flow.entity.Department;
import com.example.document_flow.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAll() {
        System.out.println(departmentRepository.findAll());
        return departmentRepository.findAll();
    }

    public Department addOne(Department department) {
        return departmentRepository.save(department);
    }

}
