package com.example.document_flow.service;

import com.example.document_flow.entity.User;
import com.example.document_flow.entity.enums.Permission;
import com.example.document_flow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        System.out.println(userRepository.findAll());
        return userRepository.findAll();
    }

    public User getOne(Long id) {
        return userRepository.findById(id).get();
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

    public User editPermission(Permission permission, Long id) {
        User tempUser = userRepository.findById(id).get();
        tempUser.setPermission(permission);
        return userRepository.save(tempUser);
    }

}
