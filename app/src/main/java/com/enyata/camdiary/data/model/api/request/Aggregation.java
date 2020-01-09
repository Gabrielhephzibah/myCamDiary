package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Objects;

public class Aggregation {

    private Aggregation(){
        // don't instantiate
    }

    public static class Request{

        @Expose
        private String collector_id;


        @Expose
        private List<AggregationCollection>aggregation_collections;


        public  Request(String collectorId, List aggregationCollections){
            this.collector_id = collectorId;
            this.aggregation_collections = aggregationCollections;
        }

        public String getCollectorId() {
            return collector_id;
        }

        public List<AggregationCollection> getAggregationCollections() {
            return aggregation_collections;
        }



        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }


          Aggregation.Request request = (Aggregation.Request)obj;

            if (collector_id != null ? !collector_id.equals(request.collector_id) : request.collector_id != null) {
                return false;
            }

            return aggregation_collections != null ? !aggregation_collections.equals(request.aggregation_collections) : request.aggregation_collections != null;

        }




        @Override
        public int hashCode() {
            int result = 0;

            result = 31 * result + (collector_id != null ? collector_id.hashCode() : 0);
            result = 31 * result + (aggregation_collections != null ? aggregation_collections.hashCode() : 0);

            return  result;

        }




        }



}