package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewAggregationRequest {

    private NewAggregationRequest(){
        //do not instantiate
    }

    public static class Request{
        @Expose
        @SerializedName("collector_id")
        private String collectorId;

        @Expose
        @SerializedName("aggregation_volume")
        private String aggregationVolume;

        @Expose
        @SerializedName("status_of_aggregation")
        private String statusOfAggregation;

        @Expose
        @SerializedName("status_of_test")
        private String statusOfTest;

        @Expose
        @SerializedName("reason_for_rejection")
        private String reasonForRejection;

        @Expose
        @SerializedName("churn_id")
        private String churnId;


        public Request(String collectorId, String aggregationVolume, String statusOfAggregation, String statusOfTest, String reasonForRejection, String churnId) {
            this.collectorId = collectorId;
            this.aggregationVolume = aggregationVolume;
            this.statusOfAggregation = statusOfAggregation;
            this.statusOfTest = statusOfTest;
            this.reasonForRejection = reasonForRejection;
            this.churnId = churnId;
        }

        public String getCollectorId() {
            return collectorId;
        }

        public String getAggregationVolume() {
            return aggregationVolume;
        }

        public String getStatusOfAggregation() {
            return statusOfAggregation;
        }

        public String getStatusOfTest() {
            return statusOfTest;
        }

        public String getReasonForRejection() {
            return reasonForRejection;
        }

        public String getChurnId() {
            return churnId;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Request request = (Request) obj;

            if (collectorId != null ? !collectorId.equals(request.collectorId) : request.collectorId != null) {
                return false;
            }
            if (aggregationVolume != null ? !aggregationVolume.equals(request.aggregationVolume) : request.aggregationVolume != null) {
                return false;
            }
            if (statusOfAggregation != null ? !statusOfAggregation.equals(request.statusOfAggregation) : request.statusOfAggregation != null) {
                return false;
            }
            if (statusOfTest != null ? !statusOfTest.equals(request.statusOfTest) : request.statusOfTest != null) {
                return false;
            }
            if (churnId != null ? !churnId.equals(request.churnId) : request.churnId != null) {
                return false;
            }

            return reasonForRejection != null ? !reasonForRejection.equals(request.reasonForRejection) : request.reasonForRejection != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (collectorId != null ? collectorId.hashCode() : 0);
            result = 31 * result + (aggregationVolume != null ? aggregationVolume.hashCode() : 0);
            result = 31 * result + (statusOfAggregation != null ? statusOfAggregation.hashCode() : 0);
            result = 31 * result + (statusOfTest != null ? statusOfTest.hashCode() : 0);
            result = 31 * result + (churnId != null ? churnId.hashCode() : 0);
            result = 31 * result + (reasonForRejection != null ? reasonForRejection.hashCode() : 0);

            return result;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "collectorId='" + collectorId + '\'' +
                    ", aggregationVolume='" + aggregationVolume + '\'' +
                    ", statusOfAggregation='" + statusOfAggregation + '\'' +
                    ", statusOfTest='" + statusOfTest + '\'' +
                    ", reasonForRejection='" + reasonForRejection + '\'' +
                    ", churnId='" + churnId + '\'' +
                    '}';
        }
    }
}
