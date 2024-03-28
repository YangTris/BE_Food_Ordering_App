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

import com.example.be_food_ordering_app.entity.Cart;
import com.example.be_food_ordering_app.entity.CartItem;
import com.example.be_food_ordering_app.service.CartService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/cart/{id}")
    public ResponseEntity<List<Cart>> getAllCartItems(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(cartService.getAllCartItem(id));
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<List<CartItem>> checkCartExist(@PathVariable String userId) throws Exception {
        return ResponseEntity.ok(cartService.checkCartItemExists(userId));
    }

    @PutMapping("/cart/{cartId}")
    public ResponseEntity<String> addFoodToCart(@PathVariable String cartId, @RequestBody CartItem cartItem)
            throws Exception {
        return ResponseEntity.ok(cartService.addToCart(cartId, cartItem));
    }

    @PostMapping("/cart")
    public ResponseEntity<String> createCart(@PathVariable String cartId, @RequestBody CartItem cartItem)
            throws Exception {
        return ResponseEntity.ok(cartService.createCart(cartId, cartItem));
    }

    // @PostMapping("/cart")
    // public ResponseEntity<String> addFoodToCart(@RequestBody CartItem cart)
    // throws Exception {
    // return ResponseEntity.ok(cartService.addFoodToCart(cart));
    // }

    // @PutMapping("/cart")
    // public ResponseEntity<String> updateFoodCart(@RequestBody CartItem cart)
    // throws Exception {
    // return ResponseEntity.ok(cartService.updateCarByFoodId(cart));
    // }

    @GetMapping("/cart/{id}/totalPrice")
    public ResponseEntity<Double> getTotalPrice(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(cartService.getTotalPrice(id));
    }

    @GetMapping("/cart/{id}/totalQuantity")
    public ResponseEntity<Integer> getTotalQuantity(@PathVariable String id) throws Exception {
        return ResponseEntity.ok(cartService.getTotalQuantity(id));
    }

    @GetMapping("/cart/{userId}/{foodId}")
    public ResponseEntity<CartItem> checkFoodExists(@PathVariable String userId, @PathVariable String foodId)
            throws Exception {
        return ResponseEntity.ok(cartService.getCartId(userId, foodId));
    }

    @DeleteMapping("/cartItem/{cartId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable String cartId) {
        return ResponseEntity.ok(cartService.deleteCartItem(cartId));
    }

    @DeleteMapping("/cart/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable String userId) throws Exception {
        return ResponseEntity.ok(cartService.clearCartByUserId(userId));
    }

}
