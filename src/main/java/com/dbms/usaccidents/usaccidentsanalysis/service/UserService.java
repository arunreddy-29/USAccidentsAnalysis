package com.dbms.usaccidents.usaccidentsanalysis.service;

import com.dbms.usaccidents.usaccidentsanalysis.entity.LoginRequest;
import com.dbms.usaccidents.usaccidentsanalysis.entity.UserEntity;
import com.dbms.usaccidents.usaccidentsanalysis.repository.UserRepository;
import com.dbms.usaccidents.usaccidentsanalysis.schema.SignupDto;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public ResponseEntity<String> createUser(SignupDto user) {

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

    @Transactional
    public ResponseEntity<String> login(LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findByLoginid(loginRequest.getLoginid());
        if (userEntity == null) {
            return ResponseEntity.badRequest().body("Invalid login ID.");
        }
        if (!userEntity.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password.");
        }
        return ResponseEntity.ok("Login successful.");
    }
}
