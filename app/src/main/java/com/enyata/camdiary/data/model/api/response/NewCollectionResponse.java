package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewCollectionResponse {

    @Expose
    @SerializedName("responseMessage")
    private String responseMessage;

    @Expose
    @SerializedName("responseCode")
    private String responseCode;

    @Expose
    @SerializedName("message")
    private String message;


    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getMessage() { return message; }
}
