package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingDeliveryResponse {

    @Expose
    @SerializedName("data")
    private List<PendingData>data;

    public List<PendingData> getData() {
        return data;
    }
}
