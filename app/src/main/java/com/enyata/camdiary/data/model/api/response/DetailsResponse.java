package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsResponse {


    @Expose
    @SerializedName("data")
    private Details data;

    @Expose
    @SerializedName("error")
    private String error;



    public Details getData() {
        return data;
    }


    public  String getError(){
        return error;
    }
}
