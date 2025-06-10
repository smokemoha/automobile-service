package com.automobileapp.automobile_service.controller;

import com.automobileapp.automobile_service.model.User;
import com.automobileapp.automobile_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users" ) // Base path for all user-related endpoints
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping // Handles POST requests to /api/users
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping // Handles GET requests to /api/users
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}") // Handles GET requests to /api/users/{id}
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}") // Handles PUT requests to /api/users/{id}
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        // In a real app, you'd fetch the existing user, update fields, and then save
        // For simplicity, this example assumes the ID in the request body matches the path variable
        if (!userService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setUserId(id); // Ensure the ID from the path is used
        User updatedUser = userService.saveUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // Handles DELETE requests to /api/users/{id}
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        if (!userService.findUserById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
