

package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.data.model.api.request.Aggregation;
import com.enyata.camdiary.data.model.api.request.BdsDataRequest;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.request.ChangePasswordRequest;
import com.enyata.camdiary.data.model.api.request.Collection;
import com.enyata.camdiary.data.model.api.request.CreateAggregationRequest;
import com.enyata.camdiary.data.model.api.request.DeliveryCollection;
import com.enyata.camdiary.data.model.api.request.DispatcherSignUpRequest;
import com.enyata.camdiary.data.model.api.request.EditProfileRequest;
import com.enyata.camdiary.data.model.api.request.PdsDataRequest;
import com.enyata.camdiary.data.model.api.request.ResetPasswordRequest;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationHistoryResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.BottleInventoryResponse;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.CollectionHistory;
import com.enyata.camdiary.data.model.api.response.CollectionHistoryResponse;
import com.enyata.camdiary.data.model.api.response.CollectorDetails;
import com.enyata.camdiary.data.model.api.response.CollectorDetailsResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryCompletedResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryDetailResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryHistoryResponseData;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.data.model.api.response.ElectoralWardResponse;
import com.enyata.camdiary.data.model.api.response.GetCoperativeNameResponse;
import com.enyata.camdiary.data.model.api.response.MilkCollectionDataResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.data.model.api.response.PendingDeliveryResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
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
    // Get Collection's history
    Flowable<CollectionResponse> getAllCollection();
    //Get Collectors Details
    Single<CollectorDetailsResponse>getCollectorDetails(String verification_id);
    //Get Farmer Details
    Single<DetailsResponse> getFarmerDetails(String id);
    //Save Aggregation
    Single<SavedAggregationResponse> saveAggregation(Aggregation.Request request);
    //Pending Delivery
    Flowable<PendingDeliveryResponse>getPendingDelivery();
    //Completed Deliveries
    Single<DeliveryCompletedResponse>getDeliveryCompleted();
    //Bottle Inventory
    Single<BottleInventoryResponse>getBottleInventory();
    //  Delivery Collection
    Single<DispatcherSignUpResponse>addNewDelivery(DeliveryCollection.Request request);
    //Reset password
    Single<ResetPasswordResponse> resetPassword(ResetPasswordRequest.Request request);
    //Collection History
    Flowable<CollectionHistoryResponse>getCollectionHistory();
    //Delivery history
    Flowable<DeliveryHistoryResponseData>getDeliveryHistory();
    //AggregationHistory
    Flowable<AggregationHistoryResponse>getAggregationHistory();
    //DispatcherSignup
    Single<DispatcherSignUpResponse>dispatcherSignUp(DispatcherSignUpRequest.Request request);

    Single<DetailsResponse>getFarmerInfo(String verificationId);

    Single<ResetPasswordResponse>userChangePassword(ChangePasswordRequest.Request request);

    Single<ResetPasswordResponse>userEditProfile(EditProfileRequest.Request request);

    Single<NewCollectionResponse>submitCdsDataQuestion(CdsDataRequest.Request request);

    Single<NewCollectionResponse>submitPdsDataQuestion(PdsDataRequest.Request request);

    Single<NewCollectionResponse>submitBdsDataQuestion(BdsDataRequest.Request request);

    Single<MilkCollectionDataResponse>getMilkCollectionData(String collectorId);

    Single<NewCollectionResponse>createAggregation(CreateAggregationRequest.Request request);

    Flowable<ElectoralWardResponse>getElectoralWard(String areaCouncil);

    Flowable<GetCoperativeNameResponse>getCooperativeName();

    Flowable<DeliveryDetailResponse>getOrderDetails(String shopifyId);





}
