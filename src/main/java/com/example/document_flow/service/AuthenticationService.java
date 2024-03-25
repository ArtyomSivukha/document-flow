package com.example.document_flow.service;


import com.example.document_flow.dto.SigninDTO;
import com.example.document_flow.dto.SignupDTO;
import com.example.document_flow.entity.Address;
import com.example.document_flow.entity.Department;
import com.example.document_flow.entity.enums.Permission;
import com.example.document_flow.entity.Person;
import com.example.document_flow.entity.User;
import com.example.document_flow.repository.UserRepository;
import com.example.document_flow.util.CurrentUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public User signin(SigninDTO signinDTO) {
//        if (userRepository.findByLogin(signinDTO.getLogin()).isPresent() && userRepository.findByPassword(signinDTO.getPassword()).isPresent()) {
//            return CurrentUser.getInstance().setUser(userRepository.findByLogin(signinDTO.getLogin()).get());
//        } else {
//            throw new IllegalArgumentException();
//        }

        if (userRepository.existsByLoginAndPassword(signinDTO.getLogin(), signinDTO.getPassword())) {
            CurrentUser.getInstance().setUser(userRepository.findByLogin(signinDTO.getLogin()).get());
            return CurrentUser.getInstance().getUser();

        } else {
            throw new IllegalArgumentException();
        }
    }

    public User signup(SignupDTO signupDTO) {
        if (!signupDTO.getPassword().equals(signupDTO.getRepeatedPassword())) {
            throw new IllegalArgumentException();
        }

        if (userRepository.existsByLogin(signupDTO.getLogin())) {
            throw new IllegalArgumentException();
        }

        User user = new User();
        user.setLogin(signupDTO.getLogin());
        user.setPassword(signupDTO.getPassword());
        user.setPermission(signupDTO.getPermission());
        user.setPerson(new Person());
        user.getPerson().setAddress(new Address());
        user.getPerson().setDepartment(new Department());
        return userRepository.save(user);
    }

    public User signout() {
        return CurrentUser.getInstance().setUser(null);
    }

}
