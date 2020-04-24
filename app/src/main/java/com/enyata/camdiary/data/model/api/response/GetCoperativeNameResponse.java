package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCoperativeNameResponse {
    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("statusCode")
    private int statusCode;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("data")
    private List<String> data;


    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getData() {
        return data;
    }
}
