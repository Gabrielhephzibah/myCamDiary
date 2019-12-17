package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;

public class Collection {

    private Collection() {
        // don't instantiate
    }

    public static class Request {

        @Expose
        private String farmerId;

        @Expose
        private String statusOfCollection;

        @Expose
        private String volume;

        @Expose
        private String testOne;

        @Expose
        private String testTwo;

        @Expose
        private String testThree;

        @Expose
        private String approvedContainer;

        @Expose
        private String message;

        public Request(String farmerId, String statusOfCollection, String volume, String testOne, String testTwo,String testThree, String approvedContainer, String message) {
            this.farmerId = farmerId;
            this.statusOfCollection = statusOfCollection;
            this.volume = volume;
            this.testOne = testOne;
            this.testTwo = testTwo;
            this.testThree = testThree;
            this.approvedContainer = approvedContainer;
            this.message = message;
        }


        public String getFarmerId() {
            return farmerId;
        }

        public String getStatusOfCollection() {
            return statusOfCollection;
        }

        public String getVolume() {
            return volume;
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

        public String getApprovedContainer() {
            return approvedContainer;
        }

        public String getMessage() {
            return message;
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

            if (farmerId != null ? !farmerId.equals(request.farmerId) : request.farmerId != null) {
                return false;
            }

            if (statusOfCollection != null ? !statusOfCollection.equals(request.statusOfCollection) : request.statusOfCollection != null) {
                return false;
            }

            if (volume != null ? !volume.equals(request.volume) : request.volume != null) {
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

            if (approvedContainer != null ? !approvedContainer.equals(request.approvedContainer) : request.approvedContainer != null) {
                return false;
            }

            return message != null ? !message.equals(request.message) : request.message != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (farmerId != null ? farmerId.hashCode() : 0);
            result = 31 * result + (statusOfCollection != null ? statusOfCollection.hashCode() : 0);
            result = 31 * result + (volume != null ? volume.hashCode() : 0);
            result = 31 * result + (testOne != null ? testOne.hashCode() : 0);
            result = 31 * result + (testTwo != null ? testTwo.hashCode() : 0);
            result = 31 * result + (testThree != null ? testThree.hashCode() : 0);
            result = 31 * result + (approvedContainer != null ? approvedContainer.hashCode() : 0);
            result = 31 * result + (message != null ? message.hashCode() : 0);
            return result;
        }
    }
}
