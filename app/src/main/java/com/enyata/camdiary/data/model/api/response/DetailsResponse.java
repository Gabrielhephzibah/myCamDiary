package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

public class DetailsResponse {


    @Expose
    @SerializedName("data")
    private FarmerDetails data;

    public FarmerDetails getData() {
        return data;
    }
}