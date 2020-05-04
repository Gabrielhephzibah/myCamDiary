package com.enyata.camdiary.ui.deliveries.history;

public class DispatcherHistory implements  DeliveryItemInterface {

    public String myName;
    public String items;
    public   String number;
    public String itemId;
    public  String productCount;

    public DispatcherHistory(String myName, String items, String number, String itemId, String productCount) {
        this.myName = myName;
        this.items = items;
        this.number = number;
        this.itemId = itemId;
        this.productCount = productCount;
    }

    @Override
    public boolean isSection() {
        return false;
    }

    @Override
    public String getMyName() {
        return myName;
    }

    @Override
    public String getItems() {
        return items;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public String getProductCount() {
        return productCount;
    }


}
