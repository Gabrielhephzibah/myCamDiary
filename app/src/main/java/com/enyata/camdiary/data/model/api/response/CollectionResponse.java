package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectionResponse {

    @Expose
    @SerializedName("data")
    private List<Collection> data;

    public List<Collection> getData() {
        return data;
    }
}
