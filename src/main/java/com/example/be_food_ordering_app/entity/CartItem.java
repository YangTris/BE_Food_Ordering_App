package com.example.be_food_ordering_app.entity;

public class CartItem {
    private String cartId;
    private String userId;
    private String foodId;
    private int quantity;
    private double price;

    public CartItem() {
    }

    public CartItem(String userId, String foodId, int quantity) {
        this.userId = userId;
        this.foodId = foodId;
        this.quantity = quantity;
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
