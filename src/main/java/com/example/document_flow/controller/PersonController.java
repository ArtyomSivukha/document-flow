package com.example.document_flow.controller;


import com.example.document_flow.entity.Person;
import com.example.document_flow.service.PersonService;
import com.example.document_flow.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping()
    public ResponseEntity<?> getFromCurrentUser() {
        return new ResponseEntity<>(CurrentUser.getInstance().getUser().getPerson(), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Person person) {
        return new ResponseEntity<>(personService.update(person), HttpStatus.OK);
    }

    @PutMapping("/editDepartment/{id}")
    public ResponseEntity<?> changeDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(personService.changeDepartment(id), HttpStatus.OK);
    }

}
