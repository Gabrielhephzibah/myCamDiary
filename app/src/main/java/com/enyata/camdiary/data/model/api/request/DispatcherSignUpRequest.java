package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DispatcherSignUpRequest {
    private DispatcherSignUpRequest(){
        //do not instantiate
    }

    public static class  Request{
        @Expose
        @SerializedName("first_name")
        private String firstName;

        @Expose
        @SerializedName("last_name")
        private String lastName;

        @Expose
        @SerializedName("email")
        private String email ;

        @Expose
        @SerializedName("phone")
        private String phoneNumber;

        @Expose
        @SerializedName("address")
        private String address;

        public Request(String firstname, String lastname, String phoneNumber, String email, String address) {
            this.firstName = firstname;
            this.lastName = lastname;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
        }


        public String getFirstname() {
            return firstName;
        }

        public String getLastname() {
            return lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Request request = (Request)obj;

            if (firstName != null ? !firstName.equals(request.firstName) : request.firstName != null) {
                return false;
            }if (lastName != null ? !lastName.equals(request.lastName) : request.lastName != null) {
                return false;
            }if (phoneNumber != null ? !phoneNumber.equals(request.phoneNumber) : request.phoneNumber != null) {
                return false;
            }if (email != null ? !email.equals(request.email) : request.email != null) {
                return false;
            }return address != null ? !address.equals(request.address) : request.address != null;

        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
            result = 31 * result + (email != null ? email.hashCode() : 0);
            result = 31 * result + (address != null ? address.hashCode() : 0);
            return result;

        }

        @Override
        public String toString() {
            return "DispatcherSignup{" +
                    "firstname='" + firstName + '\'' +
                    ", lastname='" + lastName + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }

        }
}
