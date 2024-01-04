package com.mohit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohit.repository.UserRepo;

@Service
public class UserService implements UserDetailsService{

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Inside userDetailsService");

        return userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("USER NOT FOUND"));
    }
    
}
