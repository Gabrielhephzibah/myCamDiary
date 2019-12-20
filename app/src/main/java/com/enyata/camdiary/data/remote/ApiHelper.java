/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.data.model.api.BlogResponse;
import com.enyata.camdiary.data.model.api.LoginRequest;
import com.enyata.camdiary.data.model.api.LoginResponse;
import com.enyata.camdiary.data.model.api.LogoutResponse;
import com.enyata.camdiary.data.model.api.OpenSourceResponse;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.data.model.api.request.Collection;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.AggregatorCollections;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by amitshekhar on 07/07/17.
 */

public interface ApiHelper {

    Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Single<LogoutResponse> doLogoutApiCall();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    ApiHeader getApiHeader();

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();

    // Login
    Single<CamLoginResponse> login(CamLogin.Request request);
    // Create Collection
    Single<NewCollectionResponse> doCreateCollection(Collection.Request request);


    // Get accepted Volume
    Single<VolumeResponse> getAcceptedVolume();
    //get aggregation volume
    Single<AggregationVolume> getAggregationVolume();
    // Get All Collectors
    Single<NumberOfCollectors> getTotalAggregation();
    // Get Rejected Volume
    Single<VolumeResponse> getRejectedVolume();
    // Get All Entries
    Single<AllEntries> getAllEntries();
    // Get Today's collection
    Flowable<CollectionResponse> getTodaysCollection();

    // Get collector collection
    Flowable<CollectionResponse>getCollectorCollection();
    //GET Aggregator today's collection
    Flowable<AggregationCollectionResponse>getAggregatorTodayCollection();

    // Get All Collections
    Flowable<CollectionResponse> getAllCollection();
    //Get Collectors Details
    Single<DetailsResponse>getCollectorDetails(String verification_id);
    //Get Farmer Details
    Single<DetailsResponse> getFarmerDetails(String id);

}
