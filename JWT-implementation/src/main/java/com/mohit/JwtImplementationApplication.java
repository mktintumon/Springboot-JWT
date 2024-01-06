package com.mohit;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mohit.models.Role;
import com.mohit.models.User;
import com.mohit.repository.RoleRepo;
import com.mohit.repository.UserRepo;

@SpringBootApplication
public class JwtImplementationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtImplementationApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepo roleRepo , UserRepo userRepo , PasswordEncoder passwordEncoder){
		return args ->{
			if(roleRepo.findByAuthority("ADMIN").isPresent()) return;

			Role adminRole = roleRepo.save(new Role("ADMIN"));
			roleRepo.save(new Role("USER"));	

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User(1, "admin", "admin@gmail.com", passwordEncoder.encode("admin"), roles);
			userRepo.save(admin);
		};
	}

}
