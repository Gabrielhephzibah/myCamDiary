package com.enyata.camdiary.data.model.api.request;

import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class NewCreateCollectionRequest {

//    private NewCreateCollectionRequest(){
//        //Don not instantiate
//    }

//    public  static class Request{
        @Expose
        @SerializedName("farmer_id")
        private String farmerId;

        @Expose
        @SerializedName("status_of_collection")
        private String statusOfCollection;

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
        @SerializedName("churn_details")
        private List<ChurnDetailsData> churnDetails;

        public NewCreateCollectionRequest(String farmerId, String statusOfCollection, String testOne, String testTwo, String testThree, boolean approvedContainer, String message, List<ChurnDetailsData> churnDetails) {
            this.farmerId = farmerId;
            this.statusOfCollection = statusOfCollection;
            this.testOne = testOne;
            this.testTwo = testTwo;
            this.testThree = testThree;
            this.approvedContainer = approvedContainer;
            this.message = message;
            this.churnDetails = churnDetails;
        }

        public String getFarmerId() {
            return farmerId;
        }

        public String getStatusOfCollection() {
            return statusOfCollection;
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

        public boolean isApprovedContainer() {
            return approvedContainer;
        }

        public String getMessage() {
            return message;
        }

        public List<ChurnDetailsData> getChurnDetails() {
            return churnDetails;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

           NewCreateCollectionRequest request = (NewCreateCollectionRequest) obj;

            if (farmerId != null ? !farmerId.equals(request.farmerId) : request.farmerId != null) {
                return false;
            }

            if (statusOfCollection != null ? !statusOfCollection.equals(request.statusOfCollection) : request.statusOfCollection != null) {
                return false;
            }

            if (testOne != null ? !testOne.equals(request.testOne) : request.testOne != null) {
                return false;
            }

            if (testTwo != null ? !testTwo.equals(request.testTwo) : request.testTwo != null) {
                return false;
            }
            if (testThree != null ? !testThree.equals(request.testThree) : request.testThree != null) {
                return false;
            }

            if (!Objects.equals(approvedContainer, request.approvedContainer)) {
                return false;
            }

            if (message != null ? !message.equals(request.message) : request.message != null) {
                return false;
            }

            return churnDetails != null ? !churnDetails.equals(request.churnDetails) : request.churnDetails != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (farmerId != null ? farmerId.hashCode() : 0);
            result = 31 * result + (statusOfCollection != null ? statusOfCollection.hashCode() : 0);
            result = 31 * result + (testOne != null ? testOne.hashCode() : 0);
            result = 31 * result + (testTwo != null ? testTwo.hashCode() : 0);
            result = 31 * result + (testThree != null ? testThree.hashCode() : 0);
            result = 31 * result + (message != null ? message.hashCode() : 0);
            result = 31 * result + (churnDetails != null ? churnDetails.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "farmerId='" + farmerId + '\'' +
                    ", statusOfCollection='" + statusOfCollection + '\'' +
                    ", testOne='" + testOne + '\'' +
                    ", testTwo='" + testTwo + '\'' +
                    ", testThree='" + testThree + '\'' +
                    ", approvedContainer=" + approvedContainer +
                    ", message='" + message + '\'' +
                    ", churnDetails=" + churnDetails +
                    '}';
//        }
    }

}
