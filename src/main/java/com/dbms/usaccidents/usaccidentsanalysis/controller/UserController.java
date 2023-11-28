package com.dbms.usaccidents.usaccidentsanalysis.controller;

import com.dbms.usaccidents.usaccidentsanalysis.entity.LoginRequest;
import com.dbms.usaccidents.usaccidentsanalysis.schema.SignupDto;
import com.dbms.usaccidents.usaccidentsanalysis.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto user) {
        // Check if the user already exists
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
