package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;

public class FarmerId {


    private FarmerId() {
        // don't instantiate
    }

    public  static  class  Request{

        @Expose
        private String id;



        public Request( String farmer_id){
            id =farmer_id;


        }

        public  String getId(){ return id;}

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            FarmerId.Request request = (FarmerId.Request) obj;
            return id != null ? !id.equals(request.id) : request.id != null ;
        }




        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (id != null ? id.hashCode() : 0);
            return result;
        }
        }

    }
