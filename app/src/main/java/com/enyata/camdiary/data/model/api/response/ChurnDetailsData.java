package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChurnDetailsData {
    @Expose
    @SerializedName("churn_id")
    private String churnId;

    @Expose
    @SerializedName("volume")
    private String volume;

    @Expose
    @SerializedName("farmer_id")
    private String farmerId;

    @Expose
    @SerializedName("farmer")
    private Details farmer;


    public String getChurnId() {
        return churnId;
    }

    public String getVolume() {
        return volume;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public Details getFarmer() {
        return farmer;
    }

    @Override
    public String toString() {
        return "ChurnDetailsData{" +
                "churnId='" + churnId + '\'' +
                ", volume='" + volume + '\'' +
                ", farmerId='" + farmerId + '\'' +
                ", farmer=" + farmer +
                '}';
    }
}
