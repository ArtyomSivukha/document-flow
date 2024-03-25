package com.example.document_flow.controller;

import com.example.document_flow.entity.Department;
import com.example.document_flow.service.DepartmentService;
import com.example.document_flow.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(departmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getFromCurrentUser(){
        return new ResponseEntity<>(CurrentUser.getInstance().getUser().getPerson().getDepartment(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOne(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.addOne(department), HttpStatus.OK);
    }
}
