package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectionHistoryResponse {
    @Expose
    @SerializedName("data")
    private List<CollectionHistory> data;

    public List<CollectionHistory> getData() {
        return data;
    }
}
