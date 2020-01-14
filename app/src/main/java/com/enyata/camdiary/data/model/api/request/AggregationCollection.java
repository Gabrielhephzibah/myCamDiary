package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;

import java.net.PortUnreachableException;

public class AggregationCollection {

    private AggregationCollection(){
        //don't instantiate
    }

    public  static  class  Request{

        @Expose
        private String collection_id;

        @Expose
        private String farmer_id;

        @Expose
        private String collection_volume;

        @Expose
        private String collection_status;

        @Expose
        private String test_one;

        @Expose
        private String test_two;

        @Expose
        private String test_three;

        @Expose
        private String approved_container;

        @Expose
        private String message;

        @Expose
        private String aggregation_volume;

        @Expose
        private String aggregation_churno;


        public  Request(String collectionId, String farmerId, String collectionVolume, String collectionStatus, String testOne, String testTwo, String testThree, String approvedContainer, String messagee, String aggregationVolume, String aggregationChurnNo){
            this.collection_id = collectionId;
            this.farmer_id = farmerId;
            this.collection_volume = collectionVolume;
            this.collection_status = collectionStatus;
            this.test_one = testOne;
            this.test_two = testTwo;
            this.test_three = testThree;
            this.approved_container = approvedContainer;
            this.message = messagee;
            this.aggregation_volume = aggregationVolume;
            this.aggregation_churno = aggregationChurnNo;
        }


        public  String getCollectionId(){
            return  collection_id;
        }


        public String getFarmerId(){
            return farmer_id;
        }

        public  String getCollectionVolume(){
            return  collection_volume;
        }

        public String getCollectionStatus(){
            return  collection_status;
        }
        
        public  String getTestOne(){
            return test_one;
        }
        
        public  String getTestTwo(){
            return test_two;
        }
        
        public  String getTestThree(){
            return  test_three;
        }
        
        public  String getApprovedContainer(){
            return  approved_container;
        }
        
        public  String getMessage(){
            return  message;
        }
        
        public  String getAggregatorVolume(){
            return  aggregation_volume;
        }
        
        public  String getAggregatorChurnNo(){
            return  aggregation_churno;
        }


        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            
            
           AggregationCollection.Request request = (AggregationCollection.Request)obj;

            if (collection_id != null ? !collection_id.equals(request.collection_id) : request.collection_id != null) {
                return false;
            }

            if (farmer_id != null ? !farmer_id.equals(request.farmer_id) : request.farmer_id != null) {
                return false;
            }

            if (collection_volume != null ? !collection_volume.equals(request.collection_volume) : request.collection_volume != null) {
                return false;
            }

            if (collection_status != null ? !collection_status.equals(request.collection_status) : request.collection_status != null) {
                return false;
            }

            if (test_one != null ? !test_one.equals(request.test_one) : request.test_one != null) {
                return false;
            }

            if (test_two != null ? !test_two.equals(request.test_two) : request.test_two != null) {
                return false;
            }

            if (test_three != null ? !test_three.equals(request.test_three) : request.test_three != null) {
                return false;
            }

            if (approved_container != null ? !approved_container.equals(request.approved_container) : request.approved_container != null) {
                return false;
            }

            if (message != null ? !message.equals(request.message) : request.message != null) {
                return false;
            }

            if (aggregation_volume != null ? !aggregation_volume.equals(request.aggregation_volume) : request.aggregation_volume != null) {
                return false;
            }

            return aggregation_churno != null ? !aggregation_churno.equals(request.aggregation_churno) : request.aggregation_churno != null;
            
        }



        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (collection_id != null ? collection_id.hashCode() : 0);
            result = 31 * result + (farmer_id != null ? farmer_id.hashCode() : 0);
            result = 31 * result + (collection_volume != null ? collection_volume.hashCode() : 0);
            result = 31 * result + (collection_status != null ? collection_status.hashCode() : 0);
            result = 31 * result + (test_one != null ? test_one.hashCode() : 0);
            result = 31 * result + (test_two != null ? test_two.hashCode() : 0);
            result = 31 * result + (test_three != null ? test_three.hashCode() : 0);
            result = 31 * result + (approved_container != null ? approved_container.hashCode() : 0);
            result = 31 * result + (message != null ? message.hashCode() : 0);
            result = 31 * result + (aggregation_volume != null ? aggregation_volume.hashCode() : 0);
            result = 31 * result + (aggregation_churno != null ? aggregation_churno.hashCode() : 0);

            return  result;


        }
    }
}
