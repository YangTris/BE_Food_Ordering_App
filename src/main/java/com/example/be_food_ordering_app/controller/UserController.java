package com.example.be_food_ordering_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_food_ordering_app.entity.User;
import com.example.be_food_ordering_app.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(userService.getUserDetails(id));
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.loginUser(user));
    }

    @PostMapping("/user")
    public ResponseEntity<String> createCustomer(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.saveCustomer(user));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
