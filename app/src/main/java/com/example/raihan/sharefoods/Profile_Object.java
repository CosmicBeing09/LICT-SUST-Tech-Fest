package com.example.raihan.sharefoods;

import com.google.gson.annotations.SerializedName;

public class Profile_Object {

    @SerializedName("username")
String username;
    @SerializedName("first_name")
String firstname;
    @SerializedName("last_name")
String lastname;
    @SerializedName("address")
String location;
    @SerializedName("email")
String email;
    @SerializedName("address")
String address;
    @SerializedName("volunter")
String is_volunteer;
    @SerializedName("available")
String is_available;
    @SerializedName("phone_number")
    String phone;

    public Profile_Object(String username, String firstname, String lastname, String location, String email, String address, String is_volunteer, String is_available, String phone) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.email = email;
        this.address = address;
        this.is_volunteer = is_volunteer;
        this.is_available = is_available;
        this.phone = phone;
    }

    public Profile_Object() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIs_volunteer() {
        return is_volunteer;
    }

    public void setIs_volunteer(String is_volunteer) {
        this.is_volunteer = is_volunteer;
    }

    public String getIs_available() {
        return is_available;
    }

    public void setIs_available(String is_available) {
        this.is_available = is_available;
    }
}
