package com.example.raihan.sharefoods;

import com.google.gson.annotations.SerializedName;

public class Login_Object {
    @SerializedName("username")
    String username;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

    public Login_Object(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Login_Object() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
