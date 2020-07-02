package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collection {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("collector_id")
    private String collectorId;

    @Expose
    @SerializedName("farmer_id")
    private String farmerId;

    @Expose
    @SerializedName("status_of_collection")
    private String statusOfCollection;

    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @Expose
    @SerializedName("updated_at")
    private String updatedAt;

    @Expose
    @SerializedName("test_one")
    private String testOne;

    @Expose
    @SerializedName("test_two")
    private String testTwo;

    @Expose
    @SerializedName("test_three")
    private String testThree;

    @Expose
    @SerializedName("approved_container")
    private boolean approvedContainer;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("volume_collected")
    private String volumeCollected;

    @Expose
    @SerializedName("farmer")
    private Details farmerDetails;
    

    public int getId() {
        return id;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public String getCollectorId() {
        return collectorId;
    }

    public String getStatusOfCollection() {
        return statusOfCollection;
    }

    public String getVolumeCollected() {
        return volumeCollected;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getTestOne() {
        return testOne;
    }

    public String getTestTwo() {
        return testTwo;
    }

    public String getTestThree() {
        return testThree;
    }

    public boolean getApprovedContainer() {
        return approvedContainer;
    }

    public String getMessage() {
        return message;
    }

    public Details getFarmer(){return  farmerDetails;}

    @Override
    public String toString() {
        return "Collection{" +
                "id='" + id + '\'' +
                ",collectorId = '"+ collectorId +
                ", farmerId='" + farmerId + '\'' +
                ", statusOfCollection='" + statusOfCollection + '\'' +
                ", volume='" + volumeCollected + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", testOne='" + testOne + '\'' +
                ", testTwo='" + testTwo + '\'' +
                ", testThree='" + testThree + '\'' +
                ", approvedContainer='" + approvedContainer + '\'' +
                ", message='" + message + '\'' +
                ", farmer='" + farmerDetails + '\'' +
                '}';
    }
}
