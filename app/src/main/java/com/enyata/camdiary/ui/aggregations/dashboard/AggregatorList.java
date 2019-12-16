package com.enyata.camdiary.ui.aggregations.dashboard;

public class AggregatorList {

    private String fullName;
    private String companyId;
    private  String myLitres;

    public  AggregatorList(String myName, String id,String milkLitres){
        fullName = myName;

        companyId= id;

        myLitres = milkLitres;

    }

    public  void setFullName(String myName){
        fullName= myName;
    }

    public String getFullName(){
        return fullName;
    }

    public  void setCompanyId(String id){
        companyId = id;
    }

    public String getCompanyId(){
        return companyId;
    }

    public  void  setMyLitres(String milkLitres){
        myLitres = milkLitres;
    }

    public  String getMyLitres(){
        return myLitres;
    }
}
