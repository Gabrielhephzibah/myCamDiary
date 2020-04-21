package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MilkCollectionDataResponse {

    @Expose
    @SerializedName("count")
    private String count;


    @Expose
    @SerializedName("data")
    private MilkDataResponse collector;


    public String getCount() {
        return count;
    }

    public MilkDataResponse getCollector() {
        return collector;
    }
}
