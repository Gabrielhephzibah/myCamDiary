package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectionHistory {

    @Expose
    @SerializedName("date")
    private String date;

    @Expose
    @SerializedName("collection_history")
    private List<Collection> collectionHistory;


    public String getDate() {
        return  date;
    }

    public List<Collection> getCollectionHistory() {
        return collectionHistory;
    }

    @Override
    public String toString() {
        return "CollectioHistory{" +
                "date='" + date+ '\'' +
                ", collectionHistory=" + collectionHistory +
                '}';
    }
}
