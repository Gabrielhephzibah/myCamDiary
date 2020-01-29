package com.enyata.camdiary.data.model.api.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product  implements Serializable {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("quantity")
    private String quantity;

    @Expose
    @SerializedName("image_url")
    private String imageUrl;

    @Expose
    @SerializedName("product_order_id")
    private int productOrderId;

    @Expose
    @SerializedName("price")
    private String price;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getProductOrderId() {
        return productOrderId;
    }

    public String getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quqntity='" + quantity+ '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", productOrderId='" + productOrderId + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
    
}
