package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SavedAggregationResponse {
    @Expose
    @SerializedName("collector_id")
    private String collectorId;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("aggregation_collections")
    private List<SavedAggregation>aggregationCollection;


    public  String getCollectorId(){
        return  collectorId;
    }

    public  List<SavedAggregation>getAggregationCollection(){
        return aggregationCollection;
    }

    public String getMessage() {
        return message;
    }
}
