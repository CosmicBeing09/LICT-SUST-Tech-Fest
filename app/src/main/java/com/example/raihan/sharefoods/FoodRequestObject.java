package com.example.raihan.sharefoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class FoodRequestObject {

    public FoodRequestObject(String foodId, Integer donator, String location, Integer quantity, Integer expireTime, String foodDesc, String pickUpTime, String foodStatus) {
        this.foodId = foodId;
        this.donator = donator;
        this.location = location;
        this.quantity = quantity;
        this.expireTime = expireTime;
        this.foodDesc = foodDesc;
        this.pickUpTime = pickUpTime;
        this.foodStatus = foodStatus;
    }

    public FoodRequestObject() {
    }

    @SerializedName("food_id")
    @Expose
    private String foodId;
    @SerializedName("donator")
    @Expose
    private Integer donator;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("expire_time")
    @Expose
    private Integer expireTime;
    @SerializedName("food_desc")
    @Expose
    private String foodDesc;
    @SerializedName("pick_up_time")
    @Expose
    private String pickUpTime;
    @SerializedName("food_status")
    @Expose
    private String foodStatus;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public Integer getDonator() {
        return donator;
    }

    public void setDonator(Integer donator) {
        this.donator = donator;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
    }
}
