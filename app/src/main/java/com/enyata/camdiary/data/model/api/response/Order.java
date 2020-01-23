package com.enyata.camdiary.data.model.api.response;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {


    @Expose
    @SerializedName("order_id")
    private int orderId;

    @Expose
    @SerializedName("user_id")
    private int userId;

    @Expose
    @SerializedName("address")
    private String address;


    @Expose
    @SerializedName("frequency_of_delivery")
    private String frequencyOfDelivery;


    @Expose
    @SerializedName("area_of_delivery")
    private String areaOfDelivery;


    @Expose
    @SerializedName("period_of_delivery")
    private String periodOfDelivery;

    @Expose
    @SerializedName("created_at")
    private String createdAt;


    @Expose
    @SerializedName("payment_status")
    private String paymentStatus;


    @Expose
    @SerializedName("delivery_status")
    private String deliveryStatus;


    @Expose
    @SerializedName("deliverer_id")
    private String delivererId;


    @Expose
    @SerializedName("amount")
    private int amount;

    @Expose
    @SerializedName("payment_reference")
    private String  paymentReference;



    @Expose
    @SerializedName("discount_id")
    private String discountId;


    @Expose
    @SerializedName("products")
    private List<Product> products;


    @Expose
    @SerializedName("user")
    private Details user;

    @Expose
    @SerializedName("product_count")
    private String productCount;



    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public String getFrequencyOfDelivery() {
        return frequencyOfDelivery;
    }

    public String getAreaOfDelivery() {
        return areaOfDelivery;
    }

    public String getPeriodOfDelivery() {
        return periodOfDelivery;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getDelivererId() {
        return delivererId;
    }

    public int getAmount() {
        return amount;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public String getDiscount_id() {
        return discountId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Details getUsers() {
        return user;
    }

    public  String getProductCount(){return  productCount;}
}
