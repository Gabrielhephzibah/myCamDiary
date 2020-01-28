package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AggregatorCollections {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("volume")
    private int volume;


    @Expose
    @SerializedName("aggregator_id")
    private int aggregatorId;

    @Expose
    @SerializedName("churn_no")
    private int churnNo;

    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @Expose
    @SerializedName("updated_at")
    private String updatedAt;

    @Expose
    @SerializedName("collector_id")
    private String collectorId;

    @Expose
    @SerializedName("collector")
    private Details collectorDetails;

    @Expose
    @SerializedName("aggregation_total_volume")
    private  String aggregationTotalVolume;



    public int getId() {
        return id;
    }

    public  int getVolume(){return volume;}

    public  int getAggregatorId(){ return aggregatorId; }

    public  int getChurnNo(){return churnNo;}

    public  String getCreatedAt(){return createdAt;}

    public  String getUpdatedAt(){return  updatedAt;}

    public  String getCollectorId(){return  collectorId;}

    public Details getCollectorDetails(){
        return  collectorDetails;
    }

    public String getAggregationTotalVolume() {
        return aggregationTotalVolume;
    }
}
