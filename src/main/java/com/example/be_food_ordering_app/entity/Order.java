package com.example.be_food_ordering_app.entity;

import java.util.Date;

public class Order {
    private String orderId;
    private String userId;
    private String shipperId;
    private Date orderDate;
    private String orderStatus;
    private double orderTotal;
    private String userAddress;
    private String merchantAdress;
    private String userPhone;
    private String paymentMethod;
    private String paymentStatus;
    private Cart cart;

    public Order() {
    }

    public Order(String orderId, String userId, String shipperId, String userAddress, String merchantAdress,
            String userPhone, Date orderDate, String orderStatus, double orderTotal, String paymentMethod,
            String paymentStatus, Cart cart) {
        this.orderId = orderId;
        this.userId = userId;
        this.shipperId = shipperId;
        this.userAddress = userAddress;
        this.merchantAdress = merchantAdress;
        this.userPhone = userPhone;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.cart = cart;
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

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getMerchantAdress() {
        return merchantAdress;
    }

    public void setMerchantAdress(String merchantAdress) {
        this.merchantAdress = merchantAdress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

}
