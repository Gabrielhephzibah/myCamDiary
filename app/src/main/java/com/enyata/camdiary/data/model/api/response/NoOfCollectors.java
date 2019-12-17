package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoOfCollectors {

    @Expose
    @SerializedName("count")
    private String count;


    public String getCount() {
        return this.count;
    }

}
