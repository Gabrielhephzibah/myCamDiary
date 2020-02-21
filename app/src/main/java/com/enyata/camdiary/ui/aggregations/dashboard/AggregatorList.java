package com.enyata.camdiary.ui.aggregations.dashboard;

public class AggregatorList {

    private String fullName;
    private String companyId;
    private  String myLitres;
    private  String image;

    public  AggregatorList(String myName, String id,String milkLitres, String myImage){
        fullName = myName;

        companyId= id;

        myLitres = milkLitres;
        image = myImage;

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

    public String getImage() {
        return image;
    }

    public void setImage(String myImage) {
        this.image = myImage;
    }
}
