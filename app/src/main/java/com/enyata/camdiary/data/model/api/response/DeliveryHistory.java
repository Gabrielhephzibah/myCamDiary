package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryHistory {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("delivery_status")
    private String deliveryStatus ;

    @Expose
    @SerializedName("dispatcher_id")
    private int dispatcherId ;

    @Expose
    @SerializedName("shopify_order_reference")
    private String shopifyOrderReference;

    @Expose
    @SerializedName("date_completed")
    private String dateCompleted ;

    @Expose
    @SerializedName("customer_details")
    private DeliveryDetails customerDetails ;

    @Expose
    @SerializedName("products_count")
    private String productsCount ;


    public int getId() {
        return id;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public int getDispatcherId() {
        return dispatcherId;
    }

    public String getShopifyOrderReference() {
        return shopifyOrderReference;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public DeliveryDetails getCustomerDetails() {
        return customerDetails;
    }

    public String getProductsCount() {
        return productsCount;
    }
}
