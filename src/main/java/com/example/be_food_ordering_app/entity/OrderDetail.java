package com.example.be_food_ordering_app.entity;

public class OrderDetail {
    private String orderDetailId;
    private Order orderId;
    private Food foodId;
    private int quantity;
    private String price;

    public OrderDetail() {
    }

    public OrderDetail(String orderDetailId, Order orderId, Food foodId, int quantity, String price) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
