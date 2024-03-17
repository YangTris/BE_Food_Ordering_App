package com.example.be_food_ordering_app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class FoodController {
    @Autowired
    private FoodService foodService;

    @PostMapping("/food")
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.createFood(food));
    }

    @GetMapping("/food")
    public ResponseEntity<List<Food>> getAllFood() {
        return ResponseEntity.ok(foodService.getAllFood());
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFood(String id) {
        return ResponseEntity.ok(foodService.getFood(id));
    }

    @PostMapping("/food/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable("id") String id, @RequestBody Food food) {
        return ResponseEntity.ok(foodService.updateFood(id, food));
    }

    @DeleteMapping("/food/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable("id") String id) {
        foodService.deleteFood(id);
        return ResponseEntity.ok().build();
    }
}
