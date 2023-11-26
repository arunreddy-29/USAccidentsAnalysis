package com.dbms.usaccidents.usaccidentsanalysis.controller;

import com.dbms.usaccidents.usaccidentsanalysis.entity.LoginRequest;
import com.dbms.usaccidents.usaccidentsanalysis.entity.UserEntity;
import com.dbms.usaccidents.usaccidentsanalysis.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserEntity user) {
        // Check if the user already exists
        if (userRepository.existsByLoginId(user.getLoginId())) {
            return ResponseEntity.badRequest().body("Login ID already exists.");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UserEntity user = userRepository.findByLoginId(loginRequest.getLoginId());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid login credentials.");
        }
        return ResponseEntity.ok("Login successful.");
    }
}
