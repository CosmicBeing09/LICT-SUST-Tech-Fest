package com.example.raihan.sharefoods;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class FoodRequestObject implements Parcelable{

    public FoodRequestObject(Integer donator, String location, Integer quantity, Integer expireTime, String foodDesc, String pickUpTime, String foodStatus) {

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


    protected FoodRequestObject(Parcel in) {
        if (in.readByte() == 0) {
            donator = null;
        } else {
            donator = in.readInt();
        }
        location = in.readString();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        if (in.readByte() == 0) {
            expireTime = null;
        } else {
            expireTime = in.readInt();
        }
        foodDesc = in.readString();
        pickUpTime = in.readString();
        foodStatus = in.readString();
    }

    public static final Creator<FoodRequestObject> CREATOR = new Creator<FoodRequestObject>() {
        @Override
        public FoodRequestObject createFromParcel(Parcel in) {
            return new FoodRequestObject(in);
        }

        @Override
        public FoodRequestObject[] newArray(int size) {
            return new FoodRequestObject[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (donator == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(donator);
        }
        parcel.writeString(location);
        if (quantity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(quantity);
        }
        if (expireTime == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(expireTime);
        }
        parcel.writeString(foodDesc);
        parcel.writeString(pickUpTime);
        parcel.writeString(foodStatus);
    }
}
