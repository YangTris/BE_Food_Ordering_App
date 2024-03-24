package com.example.be_food_ordering_app.model;

public class Cart {
    private String cartId;
    // private User userId;
    // private Food foodId;
    private String userId;
    private String foodId;
    private int quantity;
    private double price;

    public Cart() {
    }

    public Cart(String cartId, String userId, String foodId, int quantity, double price) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
