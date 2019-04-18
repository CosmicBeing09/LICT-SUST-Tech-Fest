package com.example.raihan.sharefoods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile_Object {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("volunter")
    @Expose
    private String volunter;
    @SerializedName("avaliable")
    @Expose
    private String avaliable;


    public Profile_Object(User user, String address, String phoneNumber, String volunter, String avaliable) {
        this.user = user;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.volunter = volunter;
        this.avaliable = avaliable;
    }

    public Profile_Object() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVolunter() {
        return volunter;
    }

    public void setVolunter(String volunter) {
        this.volunter = volunter;
    }

    public String getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(String avaliable) {
        this.avaliable = avaliable;
    }

}
