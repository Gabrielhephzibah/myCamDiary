package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AggregationHistory {

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("aggregation_history")
    List<AggregatorCollections> aggregatorCollections;

    public String getDate() {
        return date;
    }

    public List<AggregatorCollections> getAggregatorCollections() {
        return aggregatorCollections;
    }
}
