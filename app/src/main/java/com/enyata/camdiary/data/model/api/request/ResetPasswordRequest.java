package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordRequest {


    private   ResetPasswordRequest(){
        //do not instantiate
    }
    public  static  class  Request{
        @Expose
        @SerializedName("email")
        private String email;


        public Request(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
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

            return email != null ? !email.equals(request.email) : request.email != null;
        }


        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (email != null ? email.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "DeliveryCollection{" +
                    "bottles='" + email + '\'' + "}";

        }




        }


}
