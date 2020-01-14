

package com.enyata.camdiary.data;

import android.content.Context;

import com.enyata.camdiary.data.model.api.request.Aggregation;
import com.enyata.camdiary.data.model.api.request.AggregationCollection;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.data.model.api.request.Collection;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.data.model.api.response.SavedAggregationResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.google.gson.Gson;
import com.enyata.camdiary.data.local.db.DbHelper;
import com.enyata.camdiary.data.local.prefs.PreferencesHelper;
import com.enyata.camdiary.data.remote.ApiHeader;
import com.enyata.camdiary.data.remote.ApiHelper;

import java.util.ArrayList;
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
    public void saveAggregationCollectionList(List<AggregationCollection.Request> list) {
        mPreferencesHelper.saveAggregationCollectionList(list);
    }

    @Override
    public List<AggregationCollection.Request> getAggregationCollectionList() {
        return mPreferencesHelper.getAggregationCollectionList();
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
    public Flowable<AggregationCollectionResponse> getAggregatorHistory() {
        return mApiHelper.getAggregatorHistory();
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
            String email) {

        setAccessToken(accessToken);
        setCurrentUserName(firstname);
        setCurrentUserEmail(email);
    }

    @Override
    public void updateLoginStatus(LoggedInMode loggedInMode) {
         setCurrentUserLoggedInMode(loggedInMode);
    }


}
