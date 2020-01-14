package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.data.model.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {

    @POST("aggregation")
    Call<Post> savePost(@Body Post collection,@Header("Authorization") String authorization);


}
