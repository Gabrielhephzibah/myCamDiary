package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VolumeResponse {

    @Expose
    @SerializedName("data")
    private String data;

    @Expose
    @SerializedName("responseCode")
    private String responseCode;

    @Expose
    @SerializedName("responseMessage")
    private String responseMessage;

    public String getData() {
        return data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
