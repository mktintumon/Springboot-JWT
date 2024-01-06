package com.mohit.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    public ResponseEntity<?> registerUser(String name ,String username, String password) {
        try {
            // Check if the user already exists
            if (userRepo.existsByUsername(username)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User already exists");
            }
    
            String encodedPassword = passwordEncoder.encode(password);
            Role userRole = roleRepo.findByAuthority("USER").orElseThrow(() -> new RuntimeException("Role not found"));
    
            Set<Role> authorities = new HashSet<>();
            authorities.add(userRole);
    
            User newUser = userRepo.save(new User(0,name, username, encodedPassword, authorities));
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
        }
    }


    public ResponseEntity<?> loginUser(String username , String password){
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            User user = userRepo.findByUsername(username).get();

            String token = tokenService.generateJwt(auth , user.getName());

            return ResponseEntity.ok(new LoginResponse(user , token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid username/Password");
        }
    }
}
