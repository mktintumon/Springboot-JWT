package com.mohit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.mohit.models.User;
// import com.mohit.repository.UserRepo;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    // @Autowired
    // private UserRepo userRepo;
    
    @GetMapping("/hello")
    public String helloUser(){
        return "Hello user level";
    }
}
