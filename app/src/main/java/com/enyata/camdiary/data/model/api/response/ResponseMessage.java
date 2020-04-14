package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMessage {

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("statusCode")
    private String statusCode;

    @Expose
    @SerializedName("status")
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }
}
