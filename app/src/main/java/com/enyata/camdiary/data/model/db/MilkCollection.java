package com.enyata.camdiary.data.model.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "milk_collection")
public class MilkCollection implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;


    @SerializedName("farmer_id")
    @ColumnInfo(name = "farmer_id")
    private  String farmerId;


    @SerializedName("status_of_collection")
    @ColumnInfo(name = "status_of_collection")
    private String statusOfCollection;


    @SerializedName("test_one")
    @ColumnInfo(name = "test_one")
    private String testOne;


    @SerializedName("test_two")
    @ColumnInfo(name = "test_two")
    private String testTwo;


    @SerializedName("test_three")
    @ColumnInfo(name = "test_three")
    private String testThree;


    @SerializedName("approved_container")
    @ColumnInfo(name = "approved_container")
    private boolean approvedContainer;


    @SerializedName("message")
    @ColumnInfo(name = "message")
    private String message;


    @TypeConverters({Converters.class})
    @SerializedName("churn_details")
    @ColumnInfo(name = "churn_details")
    private List<ChurnDetailsData>churnDetails;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getStatusOfCollection() {
        return statusOfCollection;
    }

    public void setStatusOfCollection(String statusOfCollection) {
        this.statusOfCollection = statusOfCollection;
    }

    public String getTestOne() {
        return testOne;
    }

    public void setTestOne(String testOne) {
        this.testOne = testOne;
    }

    public String getTestTwo() {
        return testTwo;
    }

    public void setTestTwo(String testTwo) {
        this.testTwo = testTwo;
    }

    public String getTestThree() {
        return testThree;
    }

    public void setTestThree(String testThree) {
        this.testThree = testThree;
    }

    public boolean isApprovedContainer() {
        return approvedContainer;
    }

    public void setApprovedContainer(boolean approvedContainer) {
        this.approvedContainer = approvedContainer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChurnDetailsData> getChurnDetails() {
        return churnDetails;
    }

    public void setChurnDetails(List<ChurnDetailsData> churnDetails) {
        this.churnDetails = churnDetails;
    }

    @Override
    public String toString() {
        return "MilkCollection{" +
                "id=" + id +
                ", farmerId='" + farmerId + '\'' +
                ", statusOfCollection='" + statusOfCollection + '\'' +
                ", testOne='" + testOne + '\'' +
                ", testTwo='" + testTwo + '\'' +
                ", testThree='" + testThree + '\'' +
                ", approvedContainer=" + approvedContainer +
                ", message='" + message + '\'' +
                ", churnDetails=" + churnDetails +
                '}';
    }
}
