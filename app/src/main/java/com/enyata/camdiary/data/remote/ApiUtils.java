package com.enyata.camdiary.data.remote;

import javax.inject.Named;

public class  ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "https://stagingcaminventoryapi.enyata.com/api/v1/";


    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }


}
