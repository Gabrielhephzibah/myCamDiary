package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FarmerIdResponse {

    @Expose
    @SerializedName("res")
    private String responseMessage;

    @Expose
    @SerializedName("responseCode")
    private String responseCode;


    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

}
