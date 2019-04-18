package com.example.raihan.sharefoods;

import java.sql.Time;

public class FoodRequestObject {
    String name;
    String location;
    String quantity;
    String briefDescription;
    String expiary;

    public FoodRequestObject(String name, String location, String quantity, String briefDescription, String expiary) {
        this.name = name;
        this.location = location;
        this.quantity = quantity;
        this.briefDescription = briefDescription;
        this.expiary = expiary;
    }

    public FoodRequestObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getExpiary() {
        return expiary;
    }

    public void setExpiary(String expiary) {
        this.expiary = expiary;
    }
}
