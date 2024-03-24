package com.example.be_food_ordering_app.model;

public class Cart {
    private String cartId;
    private User userId;
    private Food foodId;
    private int quantity;
    private double price;

    public Cart() {
    }

    public Cart(String cartId, User userId, Food foodId, int quantity, double price) {
        this.cartId = cartId;
        this.userId = userId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
