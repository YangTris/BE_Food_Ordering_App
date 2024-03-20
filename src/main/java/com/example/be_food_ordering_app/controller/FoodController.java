package com.example.be_food_ordering_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_food_ordering_app.model.Food;
import com.example.be_food_ordering_app.service.FoodService;

@RestController
public class FoodController {
    @Autowired
    FoodService foodService;

    @PostMapping("/food")
    public ResponseEntity<String> createFood(@RequestBody Food food) throws Exception {
        return ResponseEntity.ok(foodService.saveFood(food));
    }

    // @PostMapping("/food")
    // public String createFood(@RequestBody Food food) throws Exception {
    // // TODO: process POST request
    // return foodService.saveFood(food);
    // }

    @GetMapping("/food")
    public ResponseEntity<List<Food>> getAllFoods() throws Exception {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    // @DeleteMapping("/food/{id}")
    // public ResponseEntity<Void> deleteFood(@PathVariable String id) {
    // foodService.deleteFood(id);
    // return ResponseEntity.ok().build();
    // }

    @DeleteMapping("/food/{id}")
    public void deleteFood(@PathVariable String id) {
        foodService.deleteFood(id);
    }

    @PutMapping("/food/{id}")
    public ResponseEntity<String> updateFood(@PathVariable String id, Food food) throws Exception {
        return ResponseEntity.ok(foodService.updateFood(id, food));
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFoodDetails(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(foodService.getFoodDetails(id));
    }

}
