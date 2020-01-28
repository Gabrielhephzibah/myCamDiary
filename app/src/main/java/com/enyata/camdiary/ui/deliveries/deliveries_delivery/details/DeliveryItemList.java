package com.enyata.camdiary.ui.deliveries.deliveries_delivery.details;

public class DeliveryItemList {


    private  String itemName;
    private  String quantity;

    public DeliveryItemList(String itemName, String quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
