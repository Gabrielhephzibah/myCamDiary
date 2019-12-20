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

import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.data.model.api.request.Collection;
import com.enyata.camdiary.data.model.api.response.AggregatorCollections;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Sanni Michael on 10/12/2019
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }


    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<CamLoginResponse> login(CamLogin.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN_URL + "auth/signin")
                .addBodyParameter(request)
                .build()
                .getObjectSingle(CamLoginResponse.class);
    }

    @Override
    public Single<NewCollectionResponse> doCreateCollection(Collection.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.NEW_COLLECTION)
                .addBodyParameter(request)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(NewCollectionResponse.class);
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
        return Rx2AndroidNetworking.get(ApiEndPoint.COLLECTED_VOLUME_URL)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(AggregationVolume.class);
    }

    @Override
    public Single<NumberOfCollectors> getTotalAggregation() {
        return Rx2AndroidNetworking.get(ApiEndPoint.NO_OF_COLLECTORS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(NumberOfCollectors.class);
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
    public Flowable<CollectionResponse> getTodaysCollection() {
        return Rx2AndroidNetworking.get(ApiEndPoint.TODAYS_COLLECTION)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(CollectionResponse.class);
    }

    @Override
    public Flowable<CollectionResponse> getCollectorCollection() {
        return Rx2AndroidNetworking.get(ApiEndPoint.COLLECTORS_COLLECTION)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(CollectionResponse.class);
    }

    @Override
    public Flowable<AggregationCollectionResponse> getAggregatorTodayCollection() {
        return Rx2AndroidNetworking.get(ApiEndPoint.AGGREGATOR_TODAY_COLLECTION)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(AggregationCollectionResponse.class);
    }

    @Override
    public Flowable<CollectionResponse> getAllCollection() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ALL_COLLECTIONS)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(CollectionResponse.class);
    }

    @Override
    public Single<DetailsResponse> getCollectorDetails(String verification_id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.COLLECTORS_DETAILS+ "/" + verification_id)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(DetailsResponse.class);
    }

    @Override
    public Single<DetailsResponse> getFarmerDetails(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.FARMER_INFO_URL+"/"+id)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(DetailsResponse.class);
    }

}
