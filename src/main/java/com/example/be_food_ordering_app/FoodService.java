package com.example.be_food_ordering_app;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FoodService {
    private FirebaseService firebaseService;

    public Food createFood(Food food) {
        return firebaseService.createFoodinFireBase(food);
    }

    public List<Food> getAllFood() {
        return firebaseService.getAllFoodinFireBase();
    }

    public Food getFood(String id) {
        return firebaseService.getFoodFromFireBase(id);
    }

    public Food updateFood(String id, Food food) {
        return firebaseService.updateFoodInFireBase(id, food);
    }

    public void deleteFood(String id) {
        firebaseService.deleteFoodFromFireBase(id);
    }
}
