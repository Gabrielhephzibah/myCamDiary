package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @SerializedName("last_name")
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

    @Expose
    @SerializedName("verification_id")
    private String verificationId;

    @Expose
    @SerializedName("cooperative_name")
    private String cooperativeName;

    @Expose
    @SerializedName("image_url")
    private String imageUrl;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("error")
    private String error;


    @Expose
    @SerializedName("message")
    private String message;


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public String getUserType() {
        return userType;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public String getCooperativeName(){
        return cooperativeName;
    }

    public String getStatus(){
        return status;
    }

    public String getError(){
        return error;
    }
    public String getMessage(){
        return message;
    }


    public String getImageUrl() {
        return imageUrl;
    }
}
