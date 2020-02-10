package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DispatcherSignUpRequest {
    private DispatcherSignUpRequest(){
        //do not instantiate
    }

    public static class  Request{
        @Expose
        @SerializedName("firstname")
        private String firstname;

        @Expose
        @SerializedName("lastname")
        private String lastname;

        @Expose
        @SerializedName("phoneNumber")
        private String phoneNumber;

        @Expose
        @SerializedName("email")
        private String email ;

        @Expose
        @SerializedName("address")
        private String address;

        public Request(String firstname, String lastname, String phoneNumber, String email, String address) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
        }


        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
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

            if (firstname != null ? !firstname.equals(request.firstname) : request.firstname != null) {
                return false;
            }if (lastname != null ? !lastname.equals(request.lastname) : request.lastname != null) {
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
            result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
            result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
            result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
            result = 31 * result + (email != null ? email.hashCode() : 0);
            result = 31 * result + (address != null ? address.hashCode() : 0);
            return result;

        }

        @Override
        public String toString() {
            return "DispatcherSignup{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }

        }
}
