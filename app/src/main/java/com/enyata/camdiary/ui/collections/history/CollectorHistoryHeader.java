package com.enyata.camdiary.ui.collections.history;

public class CollectorHistoryHeader implements  CollectionItemInterface {
    private  String date;

    public CollectorHistoryHeader(String date){
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
    public String getCompanyName() {
        return null;
    }

    @Override
    public String getCompanyId() {
        return null;
    }

    @Override
    public String getStatus() {
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
