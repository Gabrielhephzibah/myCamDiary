package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("token")
    private String token;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("firstname")
    private String firstname;

    @Expose
    @SerializedName("lastname")
    private String lastname;

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

    @Expose
    @SerializedName("image_url")
    private String imageUrl;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getUserType() {
        return userType;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastname;
    }

    public String getFirstName() {
        return firstname;
    }

    public int getId() {
        return id;
    }

    public  String getImageUrl(){return  imageUrl;}
}
