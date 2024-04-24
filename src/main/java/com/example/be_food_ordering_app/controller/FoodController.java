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

import com.example.be_food_ordering_app.entity.Food;
import com.example.be_food_ordering_app.service.FoodService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FoodController {
    @Autowired
    FoodService foodService;

    @GetMapping("/food")
    public ResponseEntity<List<Food>> getFoods(HttpServletRequest req) throws Exception {
        return ResponseEntity.ok(foodService.getFoods(req));
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFoodDetails(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(foodService.getFoodDetails(id));
    }

    @PostMapping("/food")
    public ResponseEntity<String> createFood(@RequestBody Food food) throws Exception {
        return ResponseEntity.ok(foodService.createFood(food));
    }

    @PutMapping("/food/{id}")
    public ResponseEntity<String> updateFood(@PathVariable String id, @RequestBody Food food) throws Exception {
        return ResponseEntity.ok(foodService.updateFood(id, food));
    }

    @DeleteMapping("/food/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable String id) {
        return ResponseEntity.ok(foodService.deleteFood(id));
    }

}
