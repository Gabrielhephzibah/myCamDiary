package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllEntries {

    @Expose
    @SerializedName("data")
    private String data;

    public String getData() {
        return this.data;
    }
}
