package com.enyata.camdiary.data.model.api.request;

import android.util.Log;

import com.google.gson.annotations.Expose;

public class CamLoginRequest {

    private CamLoginRequest() {
        // don't instantiate
    }

    public static class Request {

        @Expose
        private String email;

        @Expose
        private String password;

        public Request(String email, String password) {
            Log.d("Server email:", email);
            Log.d("Server password:", password);
            this.email = email;
            this.password = password;
        }


        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
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

            if (email != null ? !email.equals(request.email) : request.email != null) {
                return false;
            }

            return password != null ? !password.equals(request.password) : request.password != null;
        }

        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (email != null ? email.hashCode() : 0);
            result = 31 * result + (password != null ? password.hashCode() : 0);
            return result;
        }
    }

}
