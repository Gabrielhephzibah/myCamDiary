package com.enyata.camdiary.ui.aggregations.product;

public class ProductList {
    private String fullName;
    private String companyName;
    private String companyId;
    private String myLitres;
    private String collectionId;


    public ProductList(String myName, String coperativeName, String companyId, String milkLitres, String id) {
        this.fullName = myName;
        this.companyName = coperativeName;
        this.companyId = companyId;
        this.myLitres = milkLitres;
        this.collectionId = id;

    }


    public void setCollectionId(String id){
        this.collectionId = id;
    }

    public String getCollectionId(){
        return this.collectionId;
    }
    public void setFullName(String myName) {
        fullName = myName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setCompanyName(String coperativeName) {
        companyName = coperativeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyId(String id) {
        companyId = id;
    }

    public String getCompanyId() {
        return companyId;
    }


    public void setMyLitres(String milkLitres) {
        myLitres = milkLitres;
    }

    public String getMyLitres() {
        return myLitres;
    }
}
