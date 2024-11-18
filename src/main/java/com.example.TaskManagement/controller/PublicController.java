package com.example.TaskManagement.controller;

import com.example.TaskManagement.model.User;
import com.example.TaskManagement.service.UserService;
import com.example.TaskManagement.service.UserServiceImplementation;
import com.example.TaskManagement.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// no auth needed
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("health-check")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("All Good");
    }

//    @PostMapping("/createUser")
//    public ResponseEntity<?> createUser(@RequestBody User user) {
//        User userData = userService.findByUserName(user.getUsername());
//        if(userData == null && userService.createUser(user)) {
//            return ResponseEntity.ok("200");
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getUsername() + " user already exists.");
//    }

    @PostMapping("/signUp")
    public  ResponseEntity<?> signUp(@RequestBody User user) {
        User userData = userService.findByUserName(user.getUsername());
        if(userData == null && userService.createUser(user)) {
            return ResponseEntity.ok("200");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getUsername() + " user already exists.");
    }

    @PostMapping("/login")
    public  ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userServiceImplementation.loadUserByUsername(user.getUsername());
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

}