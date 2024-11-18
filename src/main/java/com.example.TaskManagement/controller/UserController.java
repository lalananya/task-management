package com.example.TaskManagement.controller;

import com.example.TaskManagement.model.User;
import com.example.TaskManagement.utils.JWTUtil;
import com.example.TaskManagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

//    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

//    @PostMapping("/userlogin/{username}")
//    public ResponseEntity<?> login(@PathVariable String username) {
//        User result = userService.login(username);
//        if(result != null) {
//            return ResponseEntity.ok(result);
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//    }

    // create a path for creating new user

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
         try {
             Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
             String username = authentication.getName();
             User userToUpdated = userService.findByUserName(username);
             userToUpdated.setPassword(user.getPassword());
             userToUpdated.setUsername(user.getUsername());
             userService.createUser(userToUpdated);
             return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User Updated Successfully");
         }catch (Exception e) {
//             log.info("updateUser");
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
         }
    }

    // this can only be deleted by admin
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User userToUpdated = userService.findByUserName(username);
            userService.deleteUser(userToUpdated);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Deleted Successfully");
        } catch (Exception e ) {
//            log.info("deleteUser");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }

    }


}
