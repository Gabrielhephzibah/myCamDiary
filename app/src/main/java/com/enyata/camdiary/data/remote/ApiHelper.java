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
import com.enyata.camdiary.data.model.api.request.CamLoginRequest;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.TodayCollectionResponse;
import com.enyata.camdiary.data.model.api.response.NoOfCollectors;
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

    // Cam Diary Requests and response
    Single<CamLoginResponse> login(CamLoginRequest.Request request);
    // Get accepted Volume
    Single<VolumeResponse> getAcceptedVolume();
    //get aggregation volume

    Single<AggregationVolume> getAggregationVolume();

    Single<NoOfCollectors> getTotalAggregation();

    // Get rejected Volume
    Single<VolumeResponse> getRejectedVolume();
    // Get All Entries
    Single<AllEntries> getAllEntries();
    // Get Today's collection
    Flowable<TodayCollectionResponse> getTodaysCollection();

}
