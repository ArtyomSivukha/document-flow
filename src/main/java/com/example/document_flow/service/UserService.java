package com.example.document_flow.service;

import com.example.document_flow.entity.User;
import com.example.document_flow.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getOne(Long id) {
        if (userRepo.existsById(id)) {
            return userRepo.findById(id).get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public User addOne(User user){
        return userRepo.save(user);
    }

    public String deleteOne(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
        return "Удалён пользователь c " + id + " id";
    }

    public User editOne(Long id, User user) {
        User tempUser = userRepo.findById(id).get();
        tempUser.setLogin(user.getLogin());
        tempUser.setPassword(user.getPassword());
        return userRepo.save(tempUser);
    }
}
