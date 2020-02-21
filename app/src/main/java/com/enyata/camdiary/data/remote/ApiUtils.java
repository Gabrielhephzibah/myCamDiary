package com.enyata.camdiary.data.remote;

import javax.inject.Named;

public class  ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://stagingcaminventoryapi.enyata.com/api/v1/";

    @Named("retrofit_one")
    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

     public static final  String FARMER_URL = "http://stagingcamuserapi.enyata.com/v1/farmer/verify/";

    @Named("retrofit_two")
     public static APIService getFarmerDetails() {
         return  RetrofitClient.getClientFarmer(FARMER_URL).create(APIService.class);
     }

     public static  final String COLLECTOR_URL = "http://stagingcamuserapi.enyata.com/v1/collectors/verify/";

    @Named("retrofit_three")
     public static APIService getCollectorDetails(){
         return  RetrofitClient.getClientCollector(COLLECTOR_URL).create(APIService.class);
     }

}
