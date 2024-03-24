package com.example.be_food_ordering_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_food_ordering_app.model.Cart;
import com.example.be_food_ordering_app.service.CartService;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getAllCartItems(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(cartService.getAllCartItem(id));
    }

    @GetMapping("/cart/{id}/totalPrice")
    public ResponseEntity<Double> getTotalPrice(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(cartService.getTotalPrice(id));
    }

    @GetMapping("/cart/{id}/totalQuantity")
    public ResponseEntity<Integer> getTotalQuantity(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(cartService.getTotalQuantity(id));
    }

    @PostMapping("/cart/{userId}")
    public ResponseEntity<String> addFoodToCart(@PathVariable String userId, @RequestBody Cart cart) throws Exception {
        return ResponseEntity.ok(cartService.addFoodToCart(userId, cart));
    }

    @DeleteMapping("/cart/{id}")
    public void deleteCartItem(@PathVariable String id) {
        cartService.deleteAllCartItem(id);
    }

    @DeleteMapping("/cart/{userId}/{foodId}")
    public void deleteCartItem(@PathVariable String userId, @PathVariable String foodId) throws Exception {
        cartService.deleteCartItemByFoodId(userId, foodId);
    }

}
