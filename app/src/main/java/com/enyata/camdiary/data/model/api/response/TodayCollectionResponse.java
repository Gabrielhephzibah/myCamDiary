package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TodayCollectionResponse {

    @Expose
    @SerializedName("data")
    private List<CollectionResponse> data;

    public List<CollectionResponse> getData() {
        return data;
    }
}
