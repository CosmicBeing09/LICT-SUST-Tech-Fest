package com.example.raihan.sharefoods.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecordObject {

    public RecordObject(Integer volunter, String donatedArea, Integer beneficent, Integer donatedBy, String finished) {
        this.volunter = volunter;
        this.donatedArea = donatedArea;
        this.beneficent = beneficent;
        this.donatedBy = donatedBy;
        this.finished = finished;
    }

    public RecordObject() {
    }

    @SerializedName("volunter")
    @Expose
    private Integer volunter;
    @SerializedName("donated_area")
    @Expose
    private String donatedArea;
    @SerializedName("beneficent")
    @Expose
    private Integer beneficent;
    @SerializedName("donated_by")
    @Expose
    private Integer donatedBy;
    @SerializedName("finished")
    @Expose
    private String finished;

    public Integer getVolunter() {
        return volunter;
    }

    public void setVolunter(Integer volunter) {
        this.volunter = volunter;
    }

    public String getDonatedArea() {
        return donatedArea;
    }

    public void setDonatedArea(String donatedArea) {
        this.donatedArea = donatedArea;
    }

    public Integer getBeneficent() {
        return beneficent;
    }

    public void setBeneficent(Integer beneficent) {
        this.beneficent = beneficent;
    }

    public Integer getDonatedBy() {
        return donatedBy;
    }

    public void setDonatedBy(Integer donatedBy) {
        this.donatedBy = donatedBy;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }
}
