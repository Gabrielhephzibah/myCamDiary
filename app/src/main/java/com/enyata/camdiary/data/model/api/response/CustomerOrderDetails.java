package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerOrderDetails {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("dispatcher_id")
    private int dispatcherId;

    @Expose
    @SerializedName("shopify_order_reference")
    private String shopifyOrderReference;

    @Expose
    @SerializedName("date_assigned")
    private String dateAssigned;

    @Expose
    @SerializedName("delivery_details")
    private DeliveryDetails deliveryDetails;

    @Expose
    @SerializedName("ordered_products")
    private List<Product> ordered_products;

    public int getId() {
        return id;
    }

    public int getDispatcherId() {
        return dispatcherId;
    }

    public String getShopifyOrderReference() {
        return shopifyOrderReference;
    }

    public String getDateAssigned() {
        return dateAssigned;
    }

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public List<Product> getOrdered_products() {
        return ordered_products;
    }
}
