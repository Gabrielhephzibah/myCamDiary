package com.enyata.camdiary.ui.collections.dashboard;

import java.util.StringTokenizer;

public class DashboardCollectorList {
    private String fullName;
    private String companyName;
    private String companyId;
    private String status;
    private  String myLitres;


    public  DashboardCollectorList(String myName,String coperativeName, String id, String myStatus,String milkLitres){
       fullName = myName;
        companyName = coperativeName;
        companyId= id;
        status = myStatus;
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

    public void  setStatus(String myStatus){
        status = myStatus;
    }

    public String getStatus(){
        return status;
    }

    public  void  setMyLitres(String milkLitres){
        myLitres = milkLitres;
    }

    public  String getMyLitres(){
        return myLitres;
    }
}
