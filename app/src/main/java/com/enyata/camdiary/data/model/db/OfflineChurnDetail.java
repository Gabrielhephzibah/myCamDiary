package com.enyata.camdiary.data.model.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfflineChurnDetail {

    @SerializedName("churn_id")
    private String churnId ;

    @SerializedName("volume")
    private String volume ;


    public OfflineChurnDetail(String churnId, String volume) {
        this.churnId = churnId;
        this.volume = volume;
    }



}
