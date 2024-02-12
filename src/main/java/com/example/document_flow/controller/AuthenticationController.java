package com.example.document_flow.controller;

import com.example.document_flow.dto.SigninDTO;
import com.example.document_flow.dto.SignupDTO;
import com.example.document_flow.service.AuthenticationService;
import com.example.document_flow.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninDTO signinDTO) {
        return new ResponseEntity<>(authenticationService.signin(signinDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) {
        return new ResponseEntity<>(authenticationService.signup(signupDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signout() {
        return new ResponseEntity<>(authenticationService.signout(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/current")
    public ResponseEntity<?> current() {
        return new ResponseEntity<>(CurrentUser.getInstance().getUser(), HttpStatus.ACCEPTED);
    }
}
