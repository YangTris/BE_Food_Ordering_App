package com.example.be_food_ordering_app.entity;

import java.util.Date;

public class Order {
    private String orderId;

    private String userId;
    private String userPhone;
    private String userName;
    private String userAddress;

    private String shipperId;
    private String shipperName;

    private Date orderDate;
    private String orderStatus;
    private double orderTotal;
    private String merchantAdress;

    private String paymentMethod;
    private String paymentStatus;

    private Cart cart;

    public Order() {
    }

    public Order(String orderId, String userId, String userPhone, String userName, String userAddress, String shipperId,
            String shipperName, Date orderDate, String orderStatus, double orderTotal, String merchantAdress,
            String paymentMethod, String paymentStatus, Cart cart) {
        this.orderId = orderId;
        this.userId = userId;
        this.userPhone = userPhone;
        this.userName = userName;
        this.userAddress = userAddress;
        this.shipperId = shipperId;
        this.shipperName = shipperName;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
        this.merchantAdress = merchantAdress;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

}
