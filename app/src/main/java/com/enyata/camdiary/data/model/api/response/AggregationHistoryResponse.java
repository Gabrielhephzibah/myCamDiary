package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AggregationHistoryResponse {
    @Expose
    @SerializedName("data")
    List<AggregationHistory> aggregationHistory;

    public List<AggregationHistory> getAggregationHistory() {
        return aggregationHistory;
    }
}
