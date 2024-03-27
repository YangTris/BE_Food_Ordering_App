package com.example.be_food_ordering_app.entity;

import java.util.List;

public class Cart {
    private String cartId;
    private String userId;
    private String totalPrice;
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart(String cartId, String userId, String totalPrice, List<CartItem> cartItems) {
        this.cartId = cartId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

}
