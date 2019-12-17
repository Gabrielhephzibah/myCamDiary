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
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by amitshekhar on 07/07/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(BlogResponse.class);
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(OpenSourceResponse.class);
    }

    @Override
    public Single<CamLoginResponse> login(CamLoginRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN_URL + "auth/signin")
                .addBodyParameter(request)
                .build()
                .getObjectSingle(CamLoginResponse.class);
    }

    @Override
    public Single<VolumeResponse> getAcceptedVolume() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ACCEPTED_VOLUME_URL)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(VolumeResponse.class);
    }

    @Override
    public Single<AggregationVolume> getAggregationVolume() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ACCEPTED_VOLUME_URL)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(AggregationVolume.class);
    }

    @Override
    public Single<NoOfCollectors> getTotalAggregation() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ACCEPTED_VOLUME_URL)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(NoOfCollectors.class);
    }

    @Override
    public Single<VolumeResponse> getRejectedVolume() {
        return Rx2AndroidNetworking.get(ApiEndPoint.REJECTED_VOLUME_URL)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(VolumeResponse.class);
    }

    @Override
    public Single<AllEntries> getAllEntries() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ALL_ENTRIES)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(AllEntries.class);
    }

    @Override
    public Flowable<TodayCollectionResponse> getTodaysCollection() {
        return Rx2AndroidNetworking.get(ApiEndPoint.TODAYS_COLLECTION)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(TodayCollectionResponse.class);
    }

}
