package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateAggregationRequest {

    private  CreateAggregationRequest(){
        //do not instantiate
    }

    public  static  class  Request{
        @Expose
        @SerializedName("collector_id")
        private String collectorId;

        @Expose
        @SerializedName("aggregation_volume")
        private String aggregationVolume;


        public Request(String collectorId, String aggregationVolume) {
            this.collectorId = collectorId;
            this.aggregationVolume = aggregationVolume;
        }

        public String getCollectorId() {
            return collectorId;
        }

        public String getAggregationVolume() {
            return aggregationVolume;
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

            return aggregationVolume != null ? !aggregationVolume.equals(request.aggregationVolume) : request.aggregationVolume != null;
        }


        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (collectorId != null ? collectorId.hashCode() : 0);
            result = 31 * result + (aggregationVolume != null ? aggregationVolume.hashCode() : 0);
            return result;
        }
    }
}
