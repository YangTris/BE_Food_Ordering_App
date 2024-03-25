package com.example.be_food_ordering_app.entity;

import java.util.Date;

public class Food {
    private String id;
    private String category;
    private double price;
    private String name;
    private String description;
    private Date timestamp;
    private String imgURL;

    public Food(String id, String category, double price, String name, String description, Date timestamp,
            String imgURL) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
        this.imgURL = imgURL;
    }

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Food [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

}
