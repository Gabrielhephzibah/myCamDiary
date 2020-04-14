package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.AggregationSavedCollection;
import com.enyata.camdiary.data.model.NewResponse;
import com.google.gson.JsonObject;

import javax.inject.Named;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static APIService apiService;
    public static final String BASE_URL = "http://stagingcaminventoryapi.enyata.com/api/v1/";
    public static  final  String FARMER_URL = "http://stagingcamuserapi.enyata.com/v1/farmer/verify/";
    public static  final String COLLECTOR_URL = "http://stagingcamuserapi.enyata.com/v1/collectors/verify/";


    private static Retrofit retrofit = null;

    public static OkHttpClient createDefaultOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build();

    }

    @Named("retrofit_one")
    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(createDefaultOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
