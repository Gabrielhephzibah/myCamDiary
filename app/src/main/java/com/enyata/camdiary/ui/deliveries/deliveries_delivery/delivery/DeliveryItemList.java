package com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery;

public class DeliveryItemList {

    private String myName;
    private  String items;
    private  String number;
    private  String itemId;
    private  String date;

    public DeliveryItemList(String customerName, String deliveryItem, String phoneNumber, String deliveryId,String deliveryDate){
        myName = customerName;
        items = deliveryItem;
        number = phoneNumber;
        itemId= deliveryId;
        date = deliveryDate;
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

    public void  setItemId(String deliveryId){
        itemId= deliveryId;
    }

    public  String getItemId(){
        return itemId;
    }

    public  void  setDate(String deliveryDate){
        date = deliveryDate;
    }

    public String getDate(){
        return date;
    }
}
