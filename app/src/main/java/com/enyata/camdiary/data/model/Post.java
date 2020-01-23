package com.enyata.camdiary.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {


        @SerializedName("collector_id")
        @Expose
        private String collectorId;
        @SerializedName("aggregation_collections")
        @Expose
        private List<AggregationSavedCollection> aggregationCollections = null;

        public String getCollectorId() {
            return collectorId;
        }

        public void setCollectorId(String collectorId) {
            this.collectorId = collectorId;
        }

        public List<AggregationSavedCollection> getAggregationCollections() {
            return aggregationCollections;
        }

        public void setAggregationCollections(List<AggregationSavedCollection> aggregationCollections) {
            this.aggregationCollections = aggregationCollections;
        }

    public Post(String collectorId, List<AggregationSavedCollection> aggregationCollections) {
        this.collectorId = collectorId;
        this.aggregationCollections = aggregationCollections;
    }

    @Override
    public String toString() {
        return "Post{" +
                "collectorId='" + collectorId + '\'' +
                ", aggregationCollections=" + aggregationCollections +
                '}';
    }
}

