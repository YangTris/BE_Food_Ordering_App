package com.example.be_food_ordering_app.entity;

public class User {
    private String userId;
    private String name;
    private int roleId; // 0 is user 1 is shipper 2 is admin
    private String address;
    private String phone;
    private String email;
    private String password;
    private String userImg;

    public User() {
    }

    public User(String userId, String name, int roleId, String address, String phone, String email, String password,
            String userImg) {
        this.userId = userId;
        this.name = name;
        this.roleId = roleId;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.userImg = userImg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

}
