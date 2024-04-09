package com.example.be_food_ordering_app.entity;

public class CartItem {
    private String foodId;
    private String foodName;
    private String imgUrl;
    private int quantity;
    private double price;
    private double total;

    public CartItem() {
    }

    public CartItem(String foodId, String foodName, String imgUrl, int quantity, double price, double total) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.imgUrl = imgUrl;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
