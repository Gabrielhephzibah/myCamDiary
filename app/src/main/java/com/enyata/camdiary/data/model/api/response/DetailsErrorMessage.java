package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsErrorMessage {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("error")
    private String error;


    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
