package com.mohit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.models.LoginResponse;
import com.mohit.models.RegisterRequest;
import com.mohit.models.User;
import com.mohit.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterRequest body){
        return authService.registerUser(body.getUsername(), body.getPassword());
    }


    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody RegisterRequest body){
        return authService.loginUser(body.getUsername(), body.getPassword());
    }
}
