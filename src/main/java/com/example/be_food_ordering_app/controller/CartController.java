package com.example.be_food_ordering_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.be_food_ordering_app.entity.CartItem;
import com.example.be_food_ordering_app.service.CartService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/cart/{id}")
    public ResponseEntity<?> getCart(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(cartService.getCartId(id));
    }

    @GetMapping("/getUserCart/{userId}")
    public ResponseEntity<List<CartItem>> getUserCart(@PathVariable String userId) throws Exception {
        return ResponseEntity.ok(cartService.getUserCart(userId));
    }

    @PostMapping("/cart/{cartId}")
    public ResponseEntity<String> createCart(@PathVariable String cartId, @RequestBody CartItem cartItem)
            throws Exception {
        return ResponseEntity.ok(cartService.createCart(cartId, cartItem));
    }

    @PutMapping("/cart/{cartId}")
    public ResponseEntity<String> addFoodToCart(@PathVariable String cartId, @RequestBody CartItem cartItem)
            throws Exception {
        return ResponseEntity.ok(cartService.addToCart(cartId, cartItem));
    }

    @DeleteMapping("/cart/{cartId}/{foodId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable String cartId,@PathVariable String foodId) throws Exception {
        return ResponseEntity.ok(cartService.deleteCartItem(cartId, foodId));
    }

    @DeleteMapping("/cart/{userId}")
    public ResponseEntity<String> deleteCart(@PathVariable String userId) throws Exception {
        return ResponseEntity.ok(cartService.deleteCart(userId));
    }

}
