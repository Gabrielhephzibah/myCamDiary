package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectorCollectionResponse {
    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("statusCode")
    private int statusCode;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("data")
    private List<ChurnData> data;

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    public List<ChurnData> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CollectorCollectionResponse{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
