

package com.enyata.camdiary.data;

import android.content.Context;

import com.enyata.camdiary.data.model.AggregationSavedCollection;
import com.enyata.camdiary.data.model.api.request.Aggregation;
import com.enyata.camdiary.data.model.api.request.BdsDataRequest;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.request.ChangePasswordRequest;
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
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.BottleInventoryResponse;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.CollectionHistory;
import com.enyata.camdiary.data.model.api.response.CollectionHistoryResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryCompletedResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryHistoryResponseData;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.data.model.api.response.PendingDeliveryResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.data.model.api.response.SavedAggregationResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.google.gson.Gson;
import com.enyata.camdiary.data.local.db.DbHelper;
import com.enyata.camdiary.data.local.prefs.PreferencesHelper;
import com.enyata.camdiary.data.remote.ApiHeader;
import com.enyata.camdiary.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Gabriel Hephzibah and Sanni Michael on 10/12/2019
 */
@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAuthorization(accessToken);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public String getLoggedInView() {
        return mPreferencesHelper.getLoggedInView();
    }

    @Override
    public void setLoggedInView(String type) {
        mPreferencesHelper.setLoggedInView(type);
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }

    @Override
    public String getFarmerId() {
        return mPreferencesHelper.getFarmerId();
    }

    @Override
    public void setFarmerId(String id) {
        mPreferencesHelper.setFarmerId(id);
    }

    @Override
    public String getCollectorId() {
        return mPreferencesHelper.getCollectorId();
    }

    @Override
    public void setCollectorId(String id) {
        mPreferencesHelper.setCollectorId(id);

    }

    @Override
    public String getCollectorCollectionId() {
       return mPreferencesHelper.getCollectorCollectionId();
    }

    @Override
    public void setAggregationCollection(String collection) {
        mPreferencesHelper.setAggregationCollection(collection);
    }

    @Override
    public String getAggregationCollection() {
        return mPreferencesHelper.getAggregationCollection();
    }

    @Override
    public void setCollectorCollectionId(String id) {
        mPreferencesHelper.setCollectorCollectionId(id);

    }

    @Override
    public void setCollectorName(String collection) {
       mPreferencesHelper.setCollectorName(collection);
    }

    @Override
    public String getCollectorName() {
        return mPreferencesHelper.getCollectorName();
    }

    @Override
    public void setOrderId(String orderId) {
        mPreferencesHelper.setOrderId(orderId);
    }

    @Override
    public String getOrderId() {
        return mPreferencesHelper.getOrderId();
    }

    @Override
    public void setUserType(String user) {
        mPreferencesHelper.setUserType(user);
    }

    @Override
    public String getUserType() {
        return mPreferencesHelper.getUserType();
    }

    @Override
    public void setCustomerName(String name) {
        mPreferencesHelper.setCustomerName(name);
    }

    @Override
    public String getCustomerName() {
        return mPreferencesHelper.getCustomerName();
    }


    @Override
    public void saveAggregationCollectionList(List<AggregationSavedCollection> list) {
        mPreferencesHelper.saveAggregationCollectionList(list);
    }

    @Override
    public List<AggregationSavedCollection> getAggregationCollectionList() {
        return mPreferencesHelper.getAggregationCollectionList();
    }

    @Override
    public void setUserImageUrl(String image_url) {
        mPreferencesHelper.setUserImageUrl(image_url);
    }

    @Override
    public String getUserImageUrl() {
        return mPreferencesHelper.getUserImageUrl();
    }

    @Override
    public void setFarmerVerificationId(String farmerVerificationId) {
        mPreferencesHelper.setFarmerVerificationId(farmerVerificationId);
    }

    @Override
    public String getFarmerVerificationId() {
        return mPreferencesHelper.getFarmerVerificationId();
    }

    @Override
    public void setCollectorVerificationId(String collectorVerificationId) {
        mPreferencesHelper.setCollectorVerificationId(collectorVerificationId);
    }

    @Override
    public String getCollectorVerificationId() {
        return mPreferencesHelper.getCollectorVerificationId();
    }

    @Override
    public void setFarmerName(String name) {
        mPreferencesHelper.setFarmerName(name);
    }

    @Override
    public String getFramerName() {
        return mPreferencesHelper.getFramerName();
    }

    @Override
    public void setFarmerPhoneNumber(String phoneNumber) {
        mPreferencesHelper.setFarmerPhoneNumber(phoneNumber);

    }

    @Override
    public String getFarmerPhoneNumber() {
        return mPreferencesHelper.getFarmerPhoneNumber();
    }

    @Override
    public void setFarmerCooperativeName(String cooperativeName) {
        mPreferencesHelper.setFarmerCooperativeName(cooperativeName);

    }

    @Override
    public String getFarmerCooperativeName() {
        return mPreferencesHelper.getFarmerCooperativeName();
    }

    @Override
    public void setCurrentUserLastName(String lastName) {
        mPreferencesHelper.setCurrentUserLastName(lastName);
    }

    @Override
    public String getCurrentUserLatName() {
        return mPreferencesHelper.getCurrentUserLatName();
    }

    @Override
    public void setCurrentUserType(String userType) {
        mPreferencesHelper.setCurrentUserType(userType);

    }

    @Override
    public String getCurrentUserType() {
        return mPreferencesHelper.getCurrentUserType();
    }

    @Override
    public void setCurrentUserAddress(String userAddress) {
        mPreferencesHelper.setCurrentUserAddress(userAddress);

    }

    @Override
    public String getCurrentUserAddress() {
        return mPreferencesHelper.getCurrentUserAddress();
    }

    @Override
    public void setCurrentUserPhoneNumber(String userPhoneNumber) {
        mPreferencesHelper.setCurrentUserPhoneNumber(userPhoneNumber);

    }

    @Override
    public String getCurrentUserPhoneNumber() {
        return mPreferencesHelper.getCurrentUserPhoneNumber();
    }

    @Override
    public void setCollectorPhoneNo(String collectorPhoneNo) {
        mPreferencesHelper.setCollectorPhoneNo(collectorPhoneNo);
    }

    @Override
    public String getCollectorPhoneNo() {
        return mPreferencesHelper.getCollectorPhoneNo();
    }

    @Override
    public void setCollectorEmail(String collectorEmail) {
        mPreferencesHelper.setCollectorEmail(collectorEmail);

    }

    @Override
    public String getCollectorEmail() {
        return mPreferencesHelper.getCollectorEmail();
    }

    @Override
    public void setTimeOnStop(long currentTimeOnStop) {
        mPreferencesHelper.setTimeOnStop(currentTimeOnStop);
    }

    @Override
    public long getTimeOnStop() {
        return mPreferencesHelper.getTimeOnStop();
    }

    @Override
    public Single<CamLoginResponse> login(CamLogin.Request request) {
        return mApiHelper.login(request);
    }

    @Override
    public Single<NewCollectionResponse> doCreateCollection(Collection.Request request) {
        return mApiHelper.doCreateCollection(request);
    }

    @Override
    public Single<VolumeResponse> getAcceptedVolume() {
        return mApiHelper.getAcceptedVolume();
    }

    @Override
    public Single<AggregationVolume> getAggregationVolume() {
        return mApiHelper.getAggregationVolume();
    }

    @Override
    public Single<NumberOfCollectors> getTotalAggregation() {
        return mApiHelper.getTotalAggregation();
    }

    @Override
    public Single<VolumeResponse> getRejectedVolume() {
        return mApiHelper.getRejectedVolume();
    }

    @Override
    public Single<AllEntries> getAllEntries() {
        return mApiHelper.getAllEntries();
    }

    @Override
    public Flowable<CollectionResponse> getTodaysCollection() {
        return mApiHelper.getTodaysCollection();
    }

    @Override
    public Flowable<CollectionResponse> getCollectorCollection(String id) {
        return mApiHelper.getCollectorCollection(id);
    }


    @Override
    public Flowable<AggregationCollectionResponse> getAggregatorTodaysCollection() {
        return mApiHelper.getAggregatorTodaysCollection();
    }

    @Override
    public Flowable<CollectionResponse> getAllCollection() {
        return mApiHelper.getAllCollection();
    }

    @Override
    public Single<DetailsResponse> getCollectorDetails(String verification_id) {
        return mApiHelper.getCollectorDetails(verification_id);
    }

    @Override
    public Single<DetailsResponse> getFarmerDetails(String id) {
        return  mApiHelper.getFarmerDetails(id);
    }

    @Override
    public Single<SavedAggregationResponse> saveAggregation(Aggregation.Request request) {
        return mApiHelper.saveAggregation(request);
    }

    @Override
    public Flowable<PendingDeliveryResponse> getPendingDelivery() {
        return mApiHelper.getPendingDelivery();
    }

    @Override
    public Single<DeliveryCompletedResponse> getDeliveryCompleted() {
        return  mApiHelper.getDeliveryCompleted();
    }

    @Override
    public Single<BottleInventoryResponse> getBottleInventory() {
        return  mApiHelper.getBottleInventory();
    }

    @Override
    public Single<NewCollectionResponse> addNewDelivery(DeliveryCollection.Request request) {
        return mApiHelper.addNewDelivery(request);
    }

    @Override
    public Single<ResetPasswordResponse> resetPassword(ResetPasswordRequest.Request request) {
        return mApiHelper.resetPassword(request);
    }

    @Override
    public Flowable<CollectionHistoryResponse> getCollectionHistory() {
        return mApiHelper.getCollectionHistory();
    }

    @Override
    public Flowable<DeliveryHistoryResponseData> getDeliveryHistory() {
        return mApiHelper.getDeliveryHistory();
    }

    @Override
    public Flowable<AggregationHistoryResponse> getAggregationHistory() {
        return mApiHelper.getAggregationHistory();
    }

    @Override
    public Single<DispatcherSignUpResponse> dispatcherSignUp(DispatcherSignUpRequest.Request request) {
        return mApiHelper.dispatcherSignUp(request);
    }

    @Override
    public Single<DetailsResponse> getFarmerInfo(String verificationId) {
        return mApiHelper.getFarmerInfo(verificationId);
    }

    @Override
    public Single<ResetPasswordResponse> userChangePassword(ChangePasswordRequest.Request request) {
        return  mApiHelper.userChangePassword(request);
    }

    @Override
    public Single<ResetPasswordResponse> userEditProfile(EditProfileRequest.Request request) {
        return mApiHelper.userEditProfile(request);
    }

    @Override
    public Single<NewCollectionResponse> submitCdsDataQuestion(CdsDataRequest.Request request) {
        return mApiHelper.submitCdsDataQuestion(request);
    }

    @Override
    public Single<NewCollectionResponse> submitPdsDataQuestion(PdsDataRequest.Request request) {
        return mApiHelper.submitPdsDataQuestion(request);
    }

    @Override
    public Single<NewCollectionResponse> submitBdsDataQuestion(BdsDataRequest.Request request) {
        return mApiHelper.submitBdsDataQuestion(request);
    }

    @Override
    public void setUserAsLoggedOut() {

    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setAuthorization(accessToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            String firstname,
            String lastname,
            String userType,
            String email, String imageUrL, String phoneNo,
            String address) {

        setAccessToken(accessToken);
        setCurrentUserName(firstname);
        setCurrentUserEmail(email);
        setCurrentUserLastName(lastname);
        setCurrentUserType(userType);
        setUserImageUrl(imageUrL);
        setCurrentUserPhoneNumber(phoneNo);
        setCurrentUserAddress(address);
    }

    @Override
    public void updateLoginStatus(LoggedInMode loggedInMode) {
         setCurrentUserLoggedInMode(loggedInMode);
    }


}
