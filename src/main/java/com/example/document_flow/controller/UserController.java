package com.example.document_flow.controller;


import com.example.document_flow.entity.User;
import com.example.document_flow.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public ResponseEntity<?> getAll(){
//        User user = new User();
//        return new ResponseEntity<>(user, HttpStatus.OK);
    return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        if (userRepo.existsById(id)) {
            return new ResponseEntity<>(userRepo.findById(id),HttpStatus.OK) ;
        } else {
            return new ResponseEntity<>("Нет пользователя с " + id + " id", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/addOne")
    public ResponseEntity<?> addOne(@RequestBody User user) {
        System.out.println(user);
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            return new ResponseEntity<>("Нет такого пользователя", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Удалён пользователь c " + id + " id", HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editOne(@PathVariable Long id, @RequestBody User user) {
        User tempUser = userRepo.findById(id).get();
        tempUser.setAge(user.getAge());
        tempUser.setFirstName(user.getFirstName());
        tempUser.setSecondName(user.getSecondName());
        tempUser.setJobName(user.getJobName());
        tempUser.setHometown(user.getHometown());
        return new ResponseEntity<>(userRepo.save(tempUser), HttpStatus.OK);
    }
}
