package com.example.be_food_ordering_app.entity;

public class Order {
    private String orderId;
    private User userId;
    private User shipperId;
    private String orderDate;
    private String orderStatus;
    private String orderTotal;

    public Order() {
    }

    public Order(String orderId, User userId, User shipperId, String orderDate, String orderStatus, String orderTotal) {
        this.orderId = orderId;
        this.userId = userId;
        this.shipperId = shipperId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public User getShipperId() {
        return shipperId;
    }

    public void setShipperId(User shipperId) {
        this.shipperId = shipperId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }
}
