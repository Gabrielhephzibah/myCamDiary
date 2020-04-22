package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ElectoralWardResponse {
    @Expose
    @SerializedName("data")
    private List<String> data;

    public List<String> getData() {
        return data;
    }
}
