package com.enyata.camdiary.data.model.api.request;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {

    private  ChangePasswordRequest(){
        //do not instantiate
    }

    public static class Request {

        @Expose
        @SerializedName("password")
        private String password;

        @Expose
        @SerializedName("newPassword")
        private String newPassword;


        public Request(String password, String newPassword) {
            this.password = password;
            this.newPassword = newPassword;
        }

        public String getPassword() {
            return password;
        }

        public String getNewPassword() {
            return newPassword;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            ChangePasswordRequest.Request request = (ChangePasswordRequest.Request) obj;

            if (password != null ? !password.equals(request.password) : request.password != null) {
                return false;
            }

            return newPassword != null ? !newPassword.equals(request.newPassword) : request.newPassword != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (password != null ? password.hashCode() : 0);
            result = 31 * result + (newPassword != null ? newPassword.hashCode() : 0);
            return result;
        }


    }


}
