package com.enyata.camdiary.ui.aggregations.history;

public class AggregationHistoryHeader implements AggregationItemInterface {
    private  String date;

    public AggregationHistoryHeader(String date){
        this.date = date;
    }


    @Override
    public boolean isSection() {
        return true;
    }

    @Override
    public String getFullName() {
        return null;
    }

    @Override
    public String getCompanyId() {
        return null;
    }

    @Override
    public String getMyLitres() {
        return null;
    }

    @Override
    public String getDate() {
        return date;
    }


}
