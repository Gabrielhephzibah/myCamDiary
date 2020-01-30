package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryCollection {

    private  DeliveryCollection(){
        //do not instantiate
    }

    public static class Request {
        @Expose
        @SerializedName("bottles")
        private String bottles;

        @Expose
        @SerializedName("order_id")
        private String orderId;


        public Request(String bottles, String orderId) {
            this.bottles = bottles;
            this.orderId = orderId;
        }

        public String getBottles() {
            return bottles;
        }

        public String getOrderId() {
            return orderId;
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

            if (bottles != null ? !bottles.equals(request.bottles) : request.bottles != null) {
                return false;
            }
            return orderId != null ? !orderId.equals(request.orderId) : request.orderId != null;

        }


        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (bottles != null ? bottles.hashCode() : 0);
            result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
            return result;

        }

        @Override
        public String toString() {
            return "DeliveryCollection{" +
                    "bottles='" + bottles + '\'' +
                    ", orderId='" + orderId + '\'' +
                    '}';
        }
    }
}
