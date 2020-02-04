package com.enyata.camdiary.ui.collections.history;

public class CollectorHistory implements CollectionItemInterface  {

    public String fullName;
    public String companyName;
    public String companyId;
    public String status;
    public   String myLitres;


    public  CollectorHistory(String myName, String coperativeName, String id, String myStatus, String milkLitres){
        fullName = myName;
        companyName = coperativeName;
        companyId= id;
        status = myStatus;
        myLitres = milkLitres;

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
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String getCompanyId() {
        return companyId;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getMyLitres() {
        return myLitres;
    }

    @Override
    public String getDate() {
        return null;
    }


}
