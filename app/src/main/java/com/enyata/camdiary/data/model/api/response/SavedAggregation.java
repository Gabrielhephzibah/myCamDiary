package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SavedAggregation {

    @Expose
    @SerializedName("collection_id")
    private String collectionId;

    @Expose
    @SerializedName("farmer_id")
    private String farmerid;

    @Expose
    @SerializedName("collection_volume")
    private String collectionVolume;

    @Expose
    @SerializedName("collection_status")
    private String collectionStatus;

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
    private String approvedContainer;

    @Expose
    @SerializedName("message")
    private String message;


    @Expose
    @SerializedName("aggregation_volume")
    private String aggregationVolume;

    @Expose
    @SerializedName("aggregation_churno")
    private String aggregationChurno;

    public String getCollectionId() {
        return collectionId;
    }

    public  String getFarmerid(){
        return  farmerid;
    }

    public  String getCollectionVolume(){
        return collectionVolume;
    }

    public  String getCollectionStatus(){
        return  collectionStatus;
    }

    public  String getTestOne(){
        return testOne;
    }

    public   String getTestTwo(){
        return testTwo;
    }

    public  String getTestThree(){
        return  testThree;
    }

    public  String getApprovedContainer(){
        return approvedContainer;
    }

    public  String getMessage(){
        return  message;
    }

    public  String getAggregationVolume(){
        return aggregationVolume;
    }

    private  String getAggregationChurno(){
        return aggregationChurno;
    }
}


