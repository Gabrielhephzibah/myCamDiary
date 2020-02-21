package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.data.model.NewResponse;
import com.enyata.camdiary.data.model.Post;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.response.CollectionHistory;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {

    @POST("aggregation")
    Call<NewResponse>savePost(@Header("Authorization") String authorization, @Body Post collection);

    @GET("{verificationId}")
    Call<DetailsResponse>getfarmerDetails(@Path("verificationId")  String verificationId, @Header("Authorization") String authorization);

    @GET("{collectorVerificationId}")
    Call<DetailsResponse>GetCollectorDetails(@Path("collectorVerificationId") String verificationId, @Header("Authorization") String authorization);

}




