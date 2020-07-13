package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChurnData {

    @Expose
    @SerializedName("churn_id")
    private String churnId;

    @Expose
    @SerializedName("volume")
    private String volume;


    public String getChurnId() {
        return churnId;
    }

    public String getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "ChurnData{" +
                "churnId='" + churnId + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}
