package com.enyata.camdiary.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AggregationSavedCollection {


        @SerializedName("collection_id")
        @Expose
        private String collectionId;
        @SerializedName("farmer_id")
        @Expose
        private String farmerId;
        @SerializedName("collection_volume")
        @Expose
        private String collectionVolume;
        @SerializedName("collection_status")
        @Expose
        private String collectionStatus;
        @SerializedName("test_one")
        @Expose
        private String testOne;
        @SerializedName("test_two")
        @Expose
        private String testTwo;
        @SerializedName("test_three")
        @Expose
        private String testThree;
        @SerializedName("approved_container")
        @Expose
        private String approvedContainer;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("aggregation_volume")
        @Expose
        private String aggregationVolume;
        @SerializedName("aggregation_churno")
        @Expose
        private String aggregationChurno;

    public AggregationSavedCollection(String collectionId, String farmerId, String collectionVolume, String collectionStatus, String testOne, String testTwo, String testThree, String approvedContainer, String message, String aggregationVolume, String aggregationChurno) {
        this.collectionId = collectionId;
        this.farmerId = farmerId;
        this.collectionVolume = collectionVolume;
        this.collectionStatus = collectionStatus;
        this.testOne = testOne;
        this.testTwo = testTwo;
        this.testThree = testThree;
        this.approvedContainer = approvedContainer;
        this.message = message;
        this.aggregationVolume = aggregationVolume;
        this.aggregationChurno = aggregationChurno;
    }

    public String getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(String collectionId) {
            this.collectionId = collectionId;
        }

        public String getFarmerId() {
            return farmerId;
        }

        public void setFarmerId(String farmerId) {
            this.farmerId = farmerId;
        }

        public String getCollectionVolume() {
            return collectionVolume;
        }

        public void setCollectionVolume(String collectionVolume) {
            this.collectionVolume = collectionVolume;
        }

        public String getCollectionStatus() {
            return collectionStatus;
        }

        public void setCollectionStatus(String collectionStatus) {
            this.collectionStatus = collectionStatus;
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

        public String getApprovedContainer() {
            return approvedContainer;
        }

        public void setApprovedContainer(String approvedContainer) {
            this.approvedContainer = approvedContainer;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getAggregationVolume() {
            return aggregationVolume;
        }

        public void setAggregationVolume(String aggregationVolume) {
            this.aggregationVolume = aggregationVolume;
        }

        public String getAggregationChurno() {
            return aggregationChurno;
        }

        public void setAggregationChurno(String aggregationChurno) {
            this.aggregationChurno = aggregationChurno;
        }

    @Override
    public String toString() {
        return "AggregationSavedCollection{" +
                "collectionId='" + collectionId + '\'' +
                ", farmerId='" + farmerId + '\'' +
                ", collectionVolume='" + collectionVolume + '\'' +
                ", collectionStatus='" + collectionStatus + '\'' +
                ", testOne='" + testOne + '\'' +
                ", testTwo='" + testTwo + '\'' +
                ", testThree='" + testThree + '\'' +
                ", approvedContainer='" + approvedContainer + '\'' +
                ", message='" + message + '\'' +
                ", aggregationVolume='" + aggregationVolume + '\'' +
                ", aggregationChurno='" + aggregationChurno + '\'' +
                '}';
    }
}

