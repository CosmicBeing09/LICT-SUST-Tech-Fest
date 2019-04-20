package com.example.raihan.sharefoods.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterObject {
    @SerializedName("username")
    @Expose
    String username;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("password1")
    @Expose
    String password;

    @SerializedName("password2")
    @Expose
    String confirmPassword;

    public RegisterObject(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public RegisterObject() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
