package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class CamLoginResponse {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private User data;

    @Expose
    @SerializedName("error")
    private String error;

    public String getMessage() {
        return this.message;
    }

    public User getData() {
        return this.data;
    }

    public String getError() {
        return this.error;
    }

    public String getStatus() {
        return this.status;
    }

}
