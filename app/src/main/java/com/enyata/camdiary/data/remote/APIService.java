package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.data.model.NewResponse;
import com.enyata.camdiary.data.model.Post;
import com.enyata.camdiary.data.model.api.request.NewCreateCollectionRequest;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.response.CollectionHistory;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface  APIService {

    @POST("aggregation")
    Call<NewResponse>savePost(@Header("Authorization") String authorization, @Body Post collection);

    @POST("collection")
    Single<NewCollectionResponse>createNewCollection(@Header("Authorization")String authorization, @Body NewCreateCollectionRequest request);
//    Flowable<NewCollectionResponse>createNewCollection(@Header("Authorization") Single authorization, @Body NewCreateCollectionRequest.Request request);


}




