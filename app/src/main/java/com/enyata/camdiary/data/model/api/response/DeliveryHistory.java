package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryHistory {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("deliverer_id")
    private int delivererId;

    @Expose
    @SerializedName("bottles")
    private int bottles;

    @Expose
    @SerializedName("order_id")
    private int orderId;


    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @Expose
    @SerializedName("updated_at")
    private String updatedAt;

    @Expose
    @SerializedName("order")
    private Order order;


    public int getId() {
        return id;
    }

    public int getDelivererId() {
        return delivererId;
    }

    public int getBottles() {
        return bottles;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Order getOrder() {
        return order;
    }
}
