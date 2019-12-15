package com.enyata.camdiary.ui.aggregations.product;

public class ProductList {
    private String fullName;
    private String companyName;
    private String companyId;
    private  String myLitres;


    public  ProductList(String myName,String coperativeName, String id, String milkLitres){
        fullName = myName;
        companyName = coperativeName;
        companyId= id;
        myLitres = milkLitres;

    }


    public  void setFullName(String myName){
        fullName= myName;
    }

    public String getFullName(){
        return fullName;
    }

    public  void  setCompanyName(String coperativeName){
        companyName= coperativeName;
    }

    public String getCompanyName(){
        return companyName;
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
