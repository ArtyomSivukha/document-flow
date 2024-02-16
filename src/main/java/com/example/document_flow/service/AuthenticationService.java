package com.example.document_flow.service;


import com.example.document_flow.dto.SigninDTO;
import com.example.document_flow.dto.SignupDTO;
import com.example.document_flow.entity.Permission;
import com.example.document_flow.entity.User;
import com.example.document_flow.repository.UserRepository;
import com.example.document_flow.util.CurrentUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public User signin(SigninDTO signinDTO) {
        if (userRepository.findByLogin(signinDTO.getLogin()).isPresent() && userRepository.findByPassword(signinDTO.getPassword()).isPresent()) {
            return CurrentUser.getInstance().setUser(userRepository.findByLogin(signinDTO.getLogin()).get());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public User signup(SignupDTO signupDTO) {
        if (!signupDTO.getPassword().equals(signupDTO.getRepeatedPassword())) {
            return null;
        }

        if (userRepository.existsByLogin(signupDTO.getLogin())) {
            return null;
        }

        User user = new User();
        user.setLogin(signupDTO.getLogin());
        user.setPassword(signupDTO.getPassword());
        user.setPermission(signupDTO.getPermission());
        return userRepository.save(user);
    }

    public User signout() {
        return CurrentUser.getInstance().setUser(null);
    }

    @Transactional
    public User deleteByLogin(String login) {
        return userRepository.deleteByLogin(login).get();
    }

    public String checkedPermission(String permission){
        try {
            if(userRepository.existsByPermission(Permission.valueOf(permission))) {
                return "yes";
            }
        } catch (IllegalArgumentException E) {}
        return "no";
    }
}
