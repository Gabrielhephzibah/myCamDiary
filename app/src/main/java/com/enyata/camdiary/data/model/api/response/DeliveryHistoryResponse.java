package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeliveryHistoryResponse {
    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("delivery_history")
    private List<DeliveryHistory> deliveryHistory;

    public String getDate() {
        return date;
    }

    public List<DeliveryHistory> getDeliveryHistory() {
        return deliveryHistory;
    }
}
