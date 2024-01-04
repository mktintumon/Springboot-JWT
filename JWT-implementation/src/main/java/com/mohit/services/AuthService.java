package com.mohit.services;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohit.models.LoginResponse;
import com.mohit.models.Role;
import com.mohit.models.User;
import com.mohit.repository.RoleRepo;
import com.mohit.repository.UserRepo;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public User registerUser(String username , String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepo.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        //return userRepo.save(new username, encodedPassword, authorities));
        return userRepo.save(new User(0, username, encodedPassword, authorities));
    }

    public LoginResponse loginUser(String username , String password){
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponse(userRepo.findByUsername(username).get() , token);
        } catch (AuthenticationException e) {
            return new LoginResponse(null,"");
        }
    }
    
}
