

package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.data.model.api.request.Aggregation;
import com.enyata.camdiary.data.model.api.request.BdsDataRequest;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.request.ChangePasswordRequest;
import com.enyata.camdiary.data.model.api.request.CreateAggregationRequest;
import com.enyata.camdiary.data.model.api.request.DeliveryCollection;
import com.enyata.camdiary.data.model.api.request.DispatcherSignUpRequest;
import com.enyata.camdiary.data.model.api.request.EditProfileRequest;
import com.enyata.camdiary.data.model.api.request.PdsDataRequest;
import com.enyata.camdiary.data.model.api.request.ResetPasswordRequest;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationHistoryResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.data.model.api.request.Collection;
import com.enyata.camdiary.data.model.api.response.AggregatorCollections;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.BottleInventoryResponse;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.CollectionHistory;
import com.enyata.camdiary.data.model.api.response.CollectionHistoryResponse;
import com.enyata.camdiary.data.model.api.response.CollectorDetails;
import com.enyata.camdiary.data.model.api.response.CollectorDetailsResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryCompletedResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryHistoryResponseData;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.data.model.api.response.MilkCollectionDataResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.data.model.api.response.PendingDeliveryResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.data.model.api.response.SavedAggregationResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.ui.aggregations.milkcollection.MilkCollectionActivity;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Sanni Michael and Gabriel Hephzibah on 10/12/2019
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
    public Flowable<CollectionResponse> getCollectorCollection(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.COLLECTORS_COLLECTION + "/" + id)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(CollectionResponse.class);
    }

    @Override
    public Flowable<AggregationCollectionResponse> getAggregatorTodaysCollection() {
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
    public Single<CollectorDetailsResponse> getCollectorDetails(String verification_id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.COLLECTORS_DETAILS+ "/" + verification_id)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(CollectorDetailsResponse.class);
    }

    @Override
    public Single<DetailsResponse> getFarmerDetails(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.FARMER_INFO_URL+"/"+ id)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(DetailsResponse.class);
    }


    @Override
    public Single<SavedAggregationResponse> saveAggregation(Aggregation.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.SAVE_AGGREGATION)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(SavedAggregationResponse.class);
    }

    @Override
    public Flowable<PendingDeliveryResponse> getPendingDelivery() {
        return Rx2AndroidNetworking.get(ApiEndPoint.PENDING_DELIVERY)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(PendingDeliveryResponse.class);
    }

    @Override
    public Single<DeliveryCompletedResponse> getDeliveryCompleted() {
        return Rx2AndroidNetworking.get(ApiEndPoint.DELIVERY_COMPLETED)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(DeliveryCompletedResponse.class);
    }

    @Override
    public Single<BottleInventoryResponse> getBottleInventory() {
        return Rx2AndroidNetworking.get(ApiEndPoint.BOTTLE_INVENTORY)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(BottleInventoryResponse.class);
    }

    @Override
    public Single<NewCollectionResponse> addNewDelivery(DeliveryCollection.Request request) {
       return Rx2AndroidNetworking.post(ApiEndPoint.DELIVERY_COLLECTION)
                .addBodyParameter(request)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(NewCollectionResponse.class);
    }

    @Override
    public Single<ResetPasswordResponse> resetPassword(ResetPasswordRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.RESET_PASSWORD)
                .addBodyParameter(request)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(ResetPasswordResponse.class);
    }

    @Override
    public Flowable<CollectionHistoryResponse> getCollectionHistory() {
        return Rx2AndroidNetworking.get(ApiEndPoint.COLLECTION_HISTORY)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(CollectionHistoryResponse.class);
    }

    @Override
    public Flowable<DeliveryHistoryResponseData> getDeliveryHistory() {
        return Rx2AndroidNetworking.get(ApiEndPoint.DELIVERY_HISTORY)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(DeliveryHistoryResponseData.class);
    }

    @Override
    public Flowable<AggregationHistoryResponse> getAggregationHistory() {
        return Rx2AndroidNetworking.get(ApiEndPoint.AGGREGATION_HISTORY)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectFlowable(AggregationHistoryResponse.class);
    }

    @Override
    public Single<DispatcherSignUpResponse> dispatcherSignUp(DispatcherSignUpRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.DISPATCHER_SIGNUP)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(DispatcherSignUpResponse.class);
    }

    @Override
    public Single<DetailsResponse> getFarmerInfo(String verificationId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.FARMER_DETAILS+ "/"+ verificationId)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(DetailsResponse.class);
    }

    @Override
    public Single<ResetPasswordResponse> userChangePassword(ChangePasswordRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CHANGE_PASSWORD)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(ResetPasswordResponse.class);

    }

    @Override
    public Single<ResetPasswordResponse> userEditProfile(EditProfileRequest.Request request) {
        return Rx2AndroidNetworking.patch(ApiEndPoint.EDIT_PROFILE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(ResetPasswordResponse.class);

    }

    @Override
    public Single<NewCollectionResponse> submitCdsDataQuestion(CdsDataRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CDS_DATA_COLLECTION)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(NewCollectionResponse.class);

    }

    @Override
    public Single<NewCollectionResponse> submitPdsDataQuestion(PdsDataRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.PDS_DATA_COLLECTION)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(NewCollectionResponse.class);

    }

    @Override
    public Single<NewCollectionResponse> submitBdsDataQuestion(BdsDataRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.BDS_DATA_COLLECTION)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(NewCollectionResponse.class);
    }

    @Override
    public Single<MilkCollectionDataResponse> getMilkCollectionData(String collectorId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.MILK_COLLECTION_DATA+ "/"+ collectorId)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(MilkCollectionDataResponse.class);
    }

    @Override
    public Single<NewCollectionResponse> createAggregation(CreateAggregationRequest.Request request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.CREATE_AGGREGATION)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectSingle(NewCollectionResponse.class);
    }


}
