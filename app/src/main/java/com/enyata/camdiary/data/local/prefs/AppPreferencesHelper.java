

package com.enyata.camdiary.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.AggregationCollection;
import com.enyata.camdiary.di.PreferenceInfo;
import com.enyata.camdiary.utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sanni Michael and Gabriel Hephzibah on 10/12/2019
 */

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";

    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";

    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";

    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private static final String PREF_KEY_FARMER_ID = "PREF_KEY_FARMER_ID";

    private static final String PREF_COLLECTOR_ID= "PREF_KEY_COLLECTOR_ID";

    private  static  final String PREF_COLLECTOR_COLLECTION_ID = "PREF_KEY_COLLECTOR_COLLECTION_ID";

    private  static  final String PREF_LOGGED_IN_VIEW = "PREF_KEY_LOGGED_IN_VIEW";

    private  static  final String PREF_KEY_AGGREGATION_COLLECTION = "PREF_KEY_AGGREGATION_COLLECTION";

    private  static  final String PREF_KEY_COLLECTOR_NAME = "PREF_KEY_COLLECTOR_NAME";

    private  static  final String PREF_KEY_AGGREGATION_COLLECTION_LIST = "PREF_KEY_AGGREGATION_COLLECTION_LIST";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public Long getCurrentUserId() {
        long userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public String getLoggedInView() {
        return mPrefs.getString(PREF_LOGGED_IN_VIEW, null);
    }

    @Override
    public void setLoggedInView(String type) {
        mPrefs.edit().putString(PREF_LOGGED_IN_VIEW, type).apply();
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply();
    }

    @Override
    public String getFarmerId() {
        return mPrefs.getString(PREF_KEY_FARMER_ID,null);
    }

    @Override
    public void setFarmerId(String id) {
        mPrefs.edit().putString(PREF_KEY_FARMER_ID,id).apply();
    }

    @Override
    public String getCollectorId() {
        return mPrefs.getString(PREF_COLLECTOR_ID,null);
    }

    @Override
    public void setCollectorId(String id) {
        mPrefs.edit().putString(PREF_COLLECTOR_ID, id).apply();
    }

    @Override
    public String getCollectorCollectionId() {
        return mPrefs.getString(PREF_COLLECTOR_COLLECTION_ID,null);
    }

    @Override
    public void setAggregationCollection(String collection) {
        mPrefs.edit().putString(PREF_KEY_AGGREGATION_COLLECTION, collection).apply();
    }

    @Override
    public String getAggregationCollection() {
        return mPrefs.getString(PREF_KEY_AGGREGATION_COLLECTION,"nil");
    }

    @Override
    public void setCollectorCollectionId(String id) {
        mPrefs.edit().putString(PREF_COLLECTOR_COLLECTION_ID,id).apply();

    }

    @Override
    public void setCollectorName(String name) {
        mPrefs.edit().putString(PREF_KEY_COLLECTOR_NAME,name).apply();
    }

    @Override
    public String getCollectorName() {
        return mPrefs.getString(PREF_KEY_COLLECTOR_NAME,null);
    }

    @Override
    public void saveAggregationCollectionList(List<AggregationCollection.Request> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        mPrefs.edit().putString(PREF_KEY_AGGREGATION_COLLECTION_LIST,json).apply();
    }

    @Override
    public List<AggregationCollection.Request> getAggregationCollectionList() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_AGGREGATION_COLLECTION_LIST,null);
        Type type = new TypeToken<List<AggregationCollection.Request>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
