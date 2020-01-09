package com.enyata.camdiary.ui.aggregations.product;

public class ProductList {

    private String fullName;
    private String companyName;
    private String companyId;
    private String myLitres;
    private String collectionId;
    private String farmerId;
    private String collectionStatus;
    private String collectionVolume;
    private String testOne;
    private String testTwo;
    private String testThree;
    private String approvedContainer;
    private String message;
    private String collectorId;


    public ProductList(String myName, String coperativeName, String companyId, String milkLitres, String id, String farmerId, String collectionStatus, String collectionVolume, String testOne, String testTwo, String testThree, String approvedContainer, String message
    , String collectorId) {
        this.fullName = myName;
        this.companyName = coperativeName;
        this.companyId = companyId;
        this.myLitres = milkLitres;
        this.collectionId = id;
        this.farmerId = farmerId;
        this.collectionStatus = collectionStatus;
        this.collectionVolume = collectionVolume;
        this.testOne = testOne;
        this.testTwo = testTwo;
        this.testThree = testThree;
        this.approvedContainer = approvedContainer;
        this.message = message;
        this.collectorId = collectorId;

    }

    public void setCollectorId(String id){
        this.collectorId = id;
    }

    public String getCollectorId(){
        return this.collectorId;
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

    public  String getFarmerId(){
        return farmerId;
    }

    public  String getCollectionStatus(){
        return collectionStatus;
    }

    public  String getCollectionVolume(){
        return collectionVolume;
    }
    public  String getTestOne(){
        return  testOne;
    }
    public  String getTestTwo(){
        return  testTwo;
    }

    public  String getTestThree(){
        return testThree;
    }

    public String getApprovedContainer(){
        return  approvedContainer;
    }
    public  String getMessage(){
        return message;
    }
}
