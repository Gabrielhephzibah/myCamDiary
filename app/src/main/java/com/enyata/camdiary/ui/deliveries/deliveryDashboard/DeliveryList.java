package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

public class DeliveryList {
    private String myName;
    private  String items;
    private  String number;
    private  String itemId;


    public DeliveryList(String customerName, String deliveryItem, String phoneNumber, String deliveryId){
        myName = customerName;
        items = deliveryItem;
        number = phoneNumber;
        itemId= deliveryId;

    }


    public void setMyName(String customerName){
        myName = customerName;
    }

    public  String getMyName(){
        return  myName;
    }

    public void  setItems(String deliveryItem){
        items = deliveryItem;
    }

    public String getItems(){
        return items;
    }

    public void  setNumber(String phoneNumber){
        number = phoneNumber;
    }

    public String getNumber(){
        return number;
    }

    public void  setItemsId(String deliveryId){
        itemId= deliveryId;
    }

    public  String getItemsId(){
        return itemId;
    }



}
