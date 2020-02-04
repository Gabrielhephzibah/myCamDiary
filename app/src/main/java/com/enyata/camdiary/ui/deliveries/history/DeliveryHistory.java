package com.enyata.camdiary.ui.deliveries.history;

public class DeliveryHistory implements  DeliveryItemInterface {

    public String myName;
    public String items;
    public   String number;
    public String itemId;

    public DeliveryHistory(String myName, String items, String number, String itemId) {
        this.myName = myName;
        this.items = items;
        this.number = number;
        this.itemId = itemId;
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
}
