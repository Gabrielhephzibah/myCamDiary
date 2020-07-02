package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;

import java.net.PortUnreachableException;

public class Collection {

    private Collection() {
        // don't instantiate
    }

    public static class Request {

        @Expose
        private String farmer_id;

        @Expose
        private String status_of_collection;

        @Expose
        private String volume;

        @Expose
        private String test_one;

        @Expose
        private String churn_no;

        @Expose
        private String test_two;

        @Expose
        private String test_three;

        @Expose
        private String approved_container;

        @Expose
        private String message;





        public Request(String farmerId, String statusOfCollection, String volume, String testOne, String churnNo, String testTwo,String testThree, String approvedContainer, String message) {
            this.farmer_id = farmerId;
            this.status_of_collection = statusOfCollection;
            this.volume = volume;
            this.test_one = testOne;
            this.churn_no = churnNo;
            this.test_two = testTwo;
            this.test_three = testThree;
            this.approved_container = approvedContainer;
            this.message = message;
        }


        public String getFarmerId() {
            return farmer_id;
        }

        public String getStatusOfCollection() {
            return status_of_collection;
        }

        public String getVolume() {
            return volume;
        }

        public String getTestOne() {
            return test_one;
        }

        public String getTestTwo() {
            return test_two;
        }

        public String getTestThree() {
            return test_three;
        }

        public String getApprovedContainer() {
            return approved_container;
        }

        public String getMessage() {
            return message;
        }

        public String getChurnNo(){
            return churn_no;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

           Collection.Request request = (Collection.Request) obj;

            if (farmer_id != null ? !farmer_id.equals(request.farmer_id) : request.farmer_id != null) {
                return false;
            }

            if (status_of_collection != null ? !status_of_collection.equals(request.status_of_collection) : request.status_of_collection != null) {
                return false;
            }

            if (volume != null ? !volume.equals(request.volume) : request.volume != null) {
                return false;
            }

            if (test_one != null ? !test_one.equals(request.test_one) : request.test_one != null) {
                return false;
            }
            if (churn_no != null ? !churn_no.equals(request.churn_no) : request.churn_no != null) {
                return false;
            }

            if (test_two != null ? !test_two.equals(request.test_two) : request.test_two != null) {
                return false;
            }

            if (test_three != null ? !test_three.equals(request.test_three) : request.test_three != null) {
                return false;
            }

            if (approved_container != null ? !approved_container.equals(request.approved_container) : request.approved_container != null) {
                return false;
            }
            return message != null ? !message.equals(request.message) : request.message != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (farmer_id != null ? farmer_id.hashCode() : 0);
            result = 31 * result + (status_of_collection != null ? status_of_collection.hashCode() : 0);
            result = 31 * result + (volume != null ? volume.hashCode() : 0);
            result = 31 * result + (test_one != null ? test_one.hashCode() : 0);
            result = 31 * result + (churn_no != null ? churn_no.hashCode() : 0);
            result = 31 * result + (test_two != null ? test_two.hashCode() : 0);
            result = 31 * result + (test_three != null ? test_three.hashCode() : 0);
            result = 31 * result + (approved_container != null ? approved_container.hashCode() : 0);
            result = 31 * result + (message != null ? message.hashCode() : 0);
            return result;
        }
    }
}
