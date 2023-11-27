package com.dbms.usaccidents.usaccidentsanalysis.controller;

import com.dbms.usaccidents.usaccidentsanalysis.entity.LoginRequest;
import com.dbms.usaccidents.usaccidentsanalysis.entity.UserEntity;
import com.dbms.usaccidents.usaccidentsanalysis.repository.UserRepository;
import com.dbms.usaccidents.usaccidentsanalysis.schema.SignupDto;
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
    public ResponseEntity<String> signup(@RequestBody SignupDto user) {
        // Check if the user already exists
        if (userRepository.existsByLoginid(user.getLoginid())) {
            return ResponseEntity.badRequest().body("Login ID already exists.");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(user.getFirstname());
        userEntity.setLastname(user.getLastname());
        userEntity.setPassword(user.getPassword());
        userEntity.setLoginid(user.getLoginid());
        userRepository.save(userEntity);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        UserEntity user = userRepository.findByLoginid(loginRequest.getLoginId());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid login credentials.");
        }
        return ResponseEntity.ok("Login successful.");
    }
}
