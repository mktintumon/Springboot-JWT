package com.mohit.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    
    @GetMapping("/")
    public String helloAdmin() {
       return "Hello Admin level";
    }
    
}
