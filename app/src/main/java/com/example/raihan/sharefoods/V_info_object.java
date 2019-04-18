package com.example.raihan.sharefoods;

import com.google.gson.annotations.SerializedName;

public class V_info_object {
    @SerializedName("day")
    private int day;


    @SerializedName("response")
    private int response;

    public V_info_object() {
    }

    public V_info_object(int day, int response) {
        this.day = day;
        this.response = response;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
