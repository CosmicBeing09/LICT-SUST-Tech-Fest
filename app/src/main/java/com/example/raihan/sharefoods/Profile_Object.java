package com.example.raihan.sharefoods;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile_Object implements Parcelable {

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

    protected Profile_Object(Parcel in) {
        address = in.readString();
        phoneNumber = in.readString();
        volunter = in.readString();
        avaliable = in.readString();
    }

    public static final Creator<Profile_Object> CREATOR = new Creator<Profile_Object>() {
        @Override
        public Profile_Object createFromParcel(Parcel in) {
            return new Profile_Object(in);
        }

        @Override
        public Profile_Object[] newArray(int size) {
            return new Profile_Object[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeString(phoneNumber);
        parcel.writeString(volunter);
        parcel.writeString(avaliable);
    }
}
