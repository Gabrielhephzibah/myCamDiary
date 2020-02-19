package com.enyata.camdiary.data.remote;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://stagingcaminventoryapi.enyata.com/api/v1/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public  static  final  String USER_URL = "http://stagingcamuserapi.enyata.com/v1/farmer/verify/";

     public  static  APIService getFarmerDetails(){
         return  RetrofitClient.getClient(USER_URL).create(APIService.class);
     }


}
