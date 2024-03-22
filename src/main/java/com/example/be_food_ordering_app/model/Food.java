package com.example.be_food_ordering_app.model;

import java.util.Date;

public class Food {
    private String id;
    private String name;
    private String description;
    private Date timestamp;
    private String imgURl;

    public Food() {
    }

    public Food(String id, String name, String description, Date timestamp, String imgURl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
        this.imgURl = imgURl;
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

    public String getImgURl() {
        return imgURl;
    }

    public void setImgURl(String imgURl) {
        this.imgURl = imgURl;
    }
}
