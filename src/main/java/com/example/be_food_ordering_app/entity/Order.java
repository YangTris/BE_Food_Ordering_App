package com.example.be_food_ordering_app.entity;

import java.util.Date;

public class Order {
    private String orderId;
    private String userId;
    private String shipperId;
    private Date orderDate;
    private String orderStatus;
    private String orderTotal;

    public Order() {
    }

    public Order(String orderId, String userId, String shipperId, Date orderDate, String orderStatus,
            String orderTotal) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShipperId() {
        return shipperId;
    }

    public void setShipperId(String shipperId) {
        this.shipperId = shipperId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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
