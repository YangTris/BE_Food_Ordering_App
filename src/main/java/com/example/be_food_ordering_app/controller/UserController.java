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

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest req) throws Exception {
        return ResponseEntity.ok(userService.getUsers(req));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(userService.getUserDetails(id));
    }

    @PostMapping("/login/{phoneNumber}/{password}")
    public ResponseEntity<String> LoginUser(@PathVariable String phoneNumber,
            @PathVariable String password)
            throws Exception {
        return ResponseEntity.ok(userService.loginUser(phoneNumber, password));
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
