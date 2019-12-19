package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AggregationCollectionResponse {
    @Expose
    @SerializedName("data")
    private List<AggregatorCollections> data;

    public List<AggregatorCollections> getData() {
        return data;
    }
}
