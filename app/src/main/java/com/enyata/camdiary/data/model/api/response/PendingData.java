package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingData {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("delivery_status")
    private String deliveryStatus;

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
    private List<Product> orderedProducts;

    @Expose
    @SerializedName("product_count")
    private String productCount;


    public int getId() {
        return id;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
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

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public String  getProductCount() {
        return productCount;
    }
}
