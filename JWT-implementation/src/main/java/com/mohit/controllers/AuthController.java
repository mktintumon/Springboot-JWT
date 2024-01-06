package com.mohit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.models.RegisterRequest;
import com.mohit.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest body){
        return authService.registerUser(body.getName() , body.getUsername(), body.getPassword());
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody RegisterRequest body){
        return authService.loginUser(body.getUsername(), body.getPassword());
    }
}
