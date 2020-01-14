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
        private List<AggregationSavedCollection> aggregationCollections;

}

