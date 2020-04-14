package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfileRequest {

    private  EditProfileRequest(){
        //don't intantiate
    }

    public static class Request {

        @Expose
        @SerializedName("firstname")
        private String firstName;

        @Expose
        @SerializedName("lastname")
        private String lastName;

        @Expose
        @SerializedName("address")
        private String address;

        @Expose
        @SerializedName("phoneNumber")
        private String phoneNumber;

        @Expose
        @SerializedName("image_url")
        private String imageUrl;


        public Request(String firstName, String lastName,String address, String phoneNumber, String imageUrl) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.imageUrl = imageUrl;
        }


        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }


        public String getAddress() {
            return address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Request request = (Request) obj;

            if (firstName != null ? !firstName.equals(request.firstName) : request.firstName != null) {
                return false;
            }
            if (lastName != null ? !lastName.equals(request.lastName) : request.lastName != null) {
                return false;
            }
            if (address != null ? !address.equals(request.address) : request.address != null) {
                return false;
            }
            if (phoneNumber != null ? !phoneNumber.equals(request.phoneNumber) : request.phoneNumber != null) {
                return false;
            }

            return imageUrl != null ? !imageUrl.equals(request.imageUrl) : request.imageUrl != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            result = 31 * result + (address != null ? address.hashCode() : 0);
            result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
            result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
            return result;
        }


    }

}
