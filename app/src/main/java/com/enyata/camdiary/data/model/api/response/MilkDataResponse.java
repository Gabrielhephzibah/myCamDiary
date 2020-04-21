package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MilkDataResponse {

    @Expose
    @SerializedName("total_volume")
    private String totalVolume;

    @Expose
    @SerializedName("number_of_churns")
    private String numberOfChurns;

    @Expose
    @SerializedName("collector")
    private CollectorDetails collector;


    public String getTotalVolume() {
        return totalVolume;
    }

    public String getNumberOfChurns() {
        return numberOfChurns;
    }

    public CollectorDetails getCollector() {
        return collector;
    }
}
