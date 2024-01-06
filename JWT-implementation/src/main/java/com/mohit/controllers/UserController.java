package com.mohit.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    
    @GetMapping("/hello")
    public String helloUser(){
        return "Hello user level";
    }
}
