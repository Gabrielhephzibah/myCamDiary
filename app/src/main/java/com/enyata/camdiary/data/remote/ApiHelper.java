

package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.data.model.api.request.Aggregation;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.data.model.api.request.Collection;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.data.model.api.response.SavedAggregationResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Gabriel Hephzibah and Sanni Michael  on 10/12/2019
 */

public interface ApiHelper {

    ApiHeader getApiHeader();
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
    Flowable<CollectionResponse>getCollectorCollection(String id);
    //GET Aggregator today's collection
    Flowable<AggregationCollectionResponse> getAggregatorTodaysCollection();

    //Get Aggregator's history
    Flowable<AggregationCollectionResponse>getAggregatorHistory();
    // Get Collection's history
    Flowable<CollectionResponse> getAllCollection();
    //Get Collectors Details
    Single<DetailsResponse>getCollectorDetails(String verification_id);
    //Get Farmer Details
    Single<DetailsResponse> getFarmerDetails(String id);

    //Save Aggregation
    Single<SavedAggregationResponse> saveAggregation(Aggregation.Request request);


}
