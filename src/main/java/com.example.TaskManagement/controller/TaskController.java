package com.example.TaskManagement.controller;

import com.example.TaskManagement.model.Task;
import com.example.TaskManagement.model.User;
import com.example.TaskManagement.service.TaskService;
import com.example.TaskManagement.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(TaskController.class);

//    @Transactional
    @PostMapping("/createTasks")
    public ResponseEntity<?> createTask(@RequestBody Task task){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.findByUserName(username);
            if(user!= null) {
                taskService.createTask(task);
                return ResponseEntity.ok("Task Created Successfully");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        catch (Exception e) {
            logger.info("");
            logger.error("Bad Request | Error Occured for {} : ", task.getTaskId(), e);
            logger.warn("resolve now");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<?> getAllTasks() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.findByUserName(username);
            if(user != null) {
                List<Task> tasks = taskService.findAllTasksByUserName(username);
                if(!tasks.isEmpty()) return ResponseEntity.status(HttpStatus.ACCEPTED).body(tasks);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tasks not found");
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }

    }


    @PutMapping("/updateTasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable int taskId, @RequestBody Task task) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.findByUserName(username);
            if(user!= null && taskService.updateTask(taskId, task)) {
                return ResponseEntity.ok("Task Updated Successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }

    @DeleteMapping("/deleteTasks/{taskId}")
    public ResponseEntity<?> deleteTasks(@PathVariable int taskId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.findByUserName(username);
            if(user!= null && taskService.deleteTask(taskId)) {
                return ResponseEntity.ok("Task Deleted Successfully");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }

    }

}
