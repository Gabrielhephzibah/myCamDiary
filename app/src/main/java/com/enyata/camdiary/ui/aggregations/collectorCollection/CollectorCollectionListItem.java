package com.enyata.camdiary.ui.aggregations.collectorCollection;

public class CollectorCollectionListItem {

    private String churnId;

    private String volume;


    public CollectorCollectionListItem(String churnId, String volume) {
        this.churnId = churnId;
        this.volume = volume;
    }


    public String getChurnId() {
        return churnId;
    }

    public void setChurnId(String churnId) {
        this.churnId = churnId;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "CollectorCollectionListItem{" +
                "churnId='" + churnId + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}
