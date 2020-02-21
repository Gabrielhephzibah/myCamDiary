package com.enyata.camdiary.ui.aggregations.history;

public class AggregatorHistoryList {
    private String fullName;
    private String companyId;
    private  String myLitres;
    private  String date;
    private  String image;


    public  AggregatorHistoryList(String myName, String id,String milkLitres,String myDate, String myImage){
        fullName = myName;

        companyId= id;

        myLitres = milkLitres;
        date = myDate;
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

    public   void  setDate(String myDate){
        date = myDate;
    }

    public  String getDate(){
        return date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
