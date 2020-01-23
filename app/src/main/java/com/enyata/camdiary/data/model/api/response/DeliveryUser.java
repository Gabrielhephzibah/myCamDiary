package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryUser {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("firstname")
    private String firstName;

    @Expose
    @SerializedName("lastname")
    private String lastName;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("contact_no")
    private String contactNo;

    @Expose
    @SerializedName("contact_address")
    private String contactAddress;

    @Expose
    @SerializedName("user_type")
    private String userType;
}
