package com.example.document_flow.service;

import com.example.document_flow.entity.User;
import com.example.document_flow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOne(Long id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public User addOne(User user){
        return userRepository.save(user);
    }

    public String deleteOne(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
        return "Удалён пользователь c " + id + " id";
    }

    public User editOne(Long id, User user) {
        User tempUser = userRepository.findById(id).get();
        tempUser.setLogin(user.getLogin());
        tempUser.setPassword(user.getPassword());
        return userRepository.save(tempUser);
    }

}
