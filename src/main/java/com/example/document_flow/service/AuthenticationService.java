package com.example.document_flow.service;


import com.example.document_flow.dto.SigninDTO;
import com.example.document_flow.dto.SignupDTO;
import com.example.document_flow.entity.User;
import com.example.document_flow.repository.UserRepository;
import com.example.document_flow.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User user = new User();
        user.setLogin(signupDTO.getLogin());
        user.setPassword(signupDTO.getPassword());
        user.setPermission(signupDTO.getPermission());
        return userRepository.save(user);
    }

    public User signout() {
        return CurrentUser.getInstance().setUser(null);
    }
}
