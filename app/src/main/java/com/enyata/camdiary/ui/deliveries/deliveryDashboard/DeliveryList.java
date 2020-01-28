package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.LayoutRes;

import com.enyata.camdiary.data.model.api.response.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class  DeliveryList implements Serializable {
    private String myName;
    private  String items;
    private  String number;
    private  String itemId;
    private ArrayList<Product>products;

    private  String customerAdress;

    public DeliveryList(String myName, String items, String number, String itemId, String customerAdress, ArrayList<Product>product) {
        this.myName = myName;
        this.items = items;
        this.number = number;
        this.itemId = itemId;
        this.products = product;
        this.customerAdress = customerAdress;
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getCustomerAdreess() {
        return customerAdress;
    }

    public void setCustomerAdreess(String customerAdreess) {
        this.customerAdress = customerAdreess;
    }
}
