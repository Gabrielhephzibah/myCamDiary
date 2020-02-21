package com.enyata.camdiary.ui.aggregations.history;

public class AggregatorHistory implements  AggregationItemInterface {
    public String fullName;
    public String companyId;
    public   String myLitres;
    public  String imageHistory;

    public AggregatorHistory(String fullName, String companyId, String myLitres, String imageHistory) {
        this.fullName = fullName;
        this.companyId = companyId;
        this.myLitres = myLitres;
       this.imageHistory = imageHistory;
    }

    @Override
    public boolean isSection() {
        return false;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getCompanyId() {
        return companyId;
    }

    @Override
    public String getMyLitres() {
        return myLitres;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public String historyImage() {
        return imageHistory;
    }


}
