

package com.enyata.camdiary.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.AggregationSavedCollection;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.api.request.AggregationCollection;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
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

    private static final String PREF_COLLECTOR_ID = "PREF_KEY_COLLECTOR_ID";

    private static final String PREF_COLLECTOR_COLLECTION_ID = "PREF_KEY_COLLECTOR_COLLECTION_ID";

    private static final String PREF_LOGGED_IN_VIEW = "PREF_KEY_LOGGED_IN_VIEW";

    private static final String PREF_KEY_AGGREGATION_COLLECTION = "PREF_KEY_AGGREGATION_COLLECTION";

    private static final String PREF_KEY_COLLECTOR_NAME = "PREF_KEY_COLLECTOR_NAME";

    private static final String PREF_KEY_AGGREGATION_COLLECTION_LIST = "PREF_KEY_AGGREGATION_COLLECTION_LIST";

    private  static  final  String PREF_KEY_ORDER_ID = "PREF_KEY_ORDER_ID";

    private static  final String PREF_KEY_CUSTOMER_NAME = "PREF_KEY_CUSTOMER_NAME";

    private  static  final String PREF_KEY_USER_TYPE = "PREF_KEY_USER_TYPE";

    private  static  final  String PREF_KEY_USER_IMAGE_URL = "PREF_KEY_USER_IMAGE_URL";

    private  static  final  String PREF_KEY_FARMER_VERIFICATION_ID = "PREF_KEY_FAMER_VERIFICATION_ID";

    private  static  final  String PREF_KEY_COLLECTOR_VERIFICATION_ID = "PREF_KEY_COLLECTOR_VERIFICATION_ID";

    private  static  final  String PREF_KEY_FARMER_NAME = "PREF_KEY_FARMER_NAME";

    private static  final String PREF_KEY_FARMER_PHONE_NUMBER = "PREF_KEY_FARMER_PHONE_NUMBER";

    private  static  final  String PREF_KEY_FARMER_COPERATIVE_NAME = "PREF_KEY_FARMER_COPERATIVE_NAME";

    private  static  final  String PREF_KEY_CURRENT_USER_LAST_NAME = "PREF_KEY_CURRENT_USER_LAST_NAME";

    private  static  final  String PREF_KEY_CURRENT_USER_TYPE = "PREF_KEY_CURRENT_USER_TYPE";

    private  static  final  String PREF_KEY_CURRENT_USER_ADDRESS = "PREF_KEY_CURRENT_USER_ADDRESS";

    private  static  final  String PREF_KEY_CURRENT_USER_PHONE_NUMBER = "PREF_KEY_CURRENT_PHONE_NUMBER";

    private  static  final  String PREF_KEY_COLLECTOR_PHONE_NUMBER = "PREF_KEY_COLLECTOR_PHONE_NUMBER";

    private  static  final  String PREF_KEY_COLLECTOR_EMAIL = "PREF_KEY_COLLECTOR_EMAIL";

    private  static  final  String PREF_KEY_TIME_ON_STOP = "PREF_KEY_TIME_ON_STOP";

    private  static  final  String PREF_KEY_SHOPIFY_ID = "PREF_KEY_SHOPIFY_ID";

    private  static  final  String PREF_KEY_CHURN_DETAILS = "PREF_KEY_CHURN_DETAILS";

    private static final String PREF_KEY_OFFLINE_FARMER_ID = "PREF_KEY_OFFLINE_FARMER_ID";

    private static final String PREF_KEY_REJECTED_VOLUME = "PREF_KEY_REJECTED_VOLUME";




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
        return mPrefs.getString(PREF_KEY_FARMER_ID, null);
    }

    @Override
    public void setFarmerId(String id) {
        mPrefs.edit().putString(PREF_KEY_FARMER_ID, id).apply();
    }

    @Override
    public String getCollectorId() {
        return mPrefs.getString(PREF_COLLECTOR_ID, null);
    }

    @Override
    public void setCollectorId(String id) {
        mPrefs.edit().putString(PREF_COLLECTOR_ID, id).apply();
    }

    @Override
    public String getCollectorCollectionId() {
        return mPrefs.getString(PREF_COLLECTOR_COLLECTION_ID, null);
    }

    @Override
    public void setAggregationCollection(String collection) {
        mPrefs.edit().putString(PREF_KEY_AGGREGATION_COLLECTION, collection).apply();
    }

    @Override
    public String getAggregationCollection() {
        return mPrefs.getString(PREF_KEY_AGGREGATION_COLLECTION, "nil");
    }

    @Override
    public void setCollectorCollectionId(String id) {
        mPrefs.edit().putString(PREF_COLLECTOR_COLLECTION_ID, id).apply();

    }

    @Override
    public void setCollectorName(String name) {
        mPrefs.edit().putString(PREF_KEY_COLLECTOR_NAME, name).apply();
    }

    @Override
    public String getCollectorName() {
        return mPrefs.getString(PREF_KEY_COLLECTOR_NAME, null);

    }

    @Override
    public void setOrderId(String orderId) {
        mPrefs.edit().putString(PREF_KEY_ORDER_ID,orderId).apply();
    }

    @Override
    public String getOrderId() {
        return mPrefs.getString(PREF_KEY_ORDER_ID,null);
    }

    @Override
    public void setUserType(String user) {
        mPrefs.edit().putString(PREF_KEY_USER_TYPE,user).apply();
    }

    @Override
    public String getUserType() {
        return mPrefs.getString(PREF_KEY_USER_TYPE,null);
    }

    @Override
    public void setCustomerName(String name) {
        mPrefs.edit().putString(PREF_KEY_CUSTOMER_NAME, name).apply();
    }

    @Override
    public String getCustomerName() {
        return mPrefs.getString(PREF_KEY_CUSTOMER_NAME,null);
    }

    @Override
    public void saveAggregationCollectionList(List<AggregationSavedCollection> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        mPrefs.edit().putString(PREF_KEY_AGGREGATION_COLLECTION_LIST, json).apply();
    }

    @Override
    public List<AggregationSavedCollection> getAggregationCollectionList() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_AGGREGATION_COLLECTION_LIST, null);
        Type type = new TypeToken<List<AggregationCollection.Request>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @Override
    public void setUserImageUrl(String image_url) {
        mPrefs.edit().putString(PREF_KEY_USER_IMAGE_URL, image_url).apply();
    }

    @Override
    public String getUserImageUrl() {
        return mPrefs.getString(PREF_KEY_USER_IMAGE_URL,null);
    }

    @Override
    public void setFarmerVerificationId(String farmerVerificationId) {
        mPrefs.edit().putString(PREF_KEY_FARMER_VERIFICATION_ID, farmerVerificationId).apply();
    }

    @Override
    public String getFarmerVerificationId() {
        return mPrefs.getString(PREF_KEY_FARMER_VERIFICATION_ID,null);
    }

    @Override
    public void setCollectorVerificationId(String collectorVerificationId) {
        mPrefs.edit().putString(PREF_KEY_COLLECTOR_VERIFICATION_ID, collectorVerificationId).apply();
    }

    @Override
    public String getCollectorVerificationId() {
        return mPrefs.getString(PREF_KEY_COLLECTOR_VERIFICATION_ID,null);
    }

    @Override
    public void setFarmerName(String name) {
        mPrefs.edit().putString(PREF_KEY_FARMER_NAME, name).apply();
    }

    @Override
    public String getFramerName() {
        return mPrefs.getString(PREF_KEY_FARMER_NAME,null);
    }

    @Override
    public void setFarmerPhoneNumber(String phoneNumber) {
        mPrefs.edit().putString(PREF_KEY_FARMER_PHONE_NUMBER,phoneNumber).apply();

    }

    @Override
    public String getFarmerPhoneNumber() {
        return mPrefs.getString(PREF_KEY_FARMER_PHONE_NUMBER,null);
    }

    @Override
    public void setFarmerCooperativeName(String cooperativeName) {
        mPrefs.edit().putString(PREF_KEY_FARMER_COPERATIVE_NAME,cooperativeName).apply();

    }

    @Override
    public String getFarmerCooperativeName() {
        return mPrefs.getString(PREF_KEY_FARMER_COPERATIVE_NAME,null);
    }

    @Override
    public void setCurrentUserLastName(String lastName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_LAST_NAME,lastName).apply();
    }

    @Override
    public String getCurrentUserLatName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_LAST_NAME,null);
    }

    @Override
    public void setCurrentUserType(String userType) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_TYPE, userType).apply();

    }

    @Override
    public String getCurrentUserType() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_TYPE,null);
    }

    @Override
    public void setCurrentUserAddress(String userAddress) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ADDRESS,userAddress).apply();

    }

    @Override
    public String getCurrentUserAddress() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_ADDRESS,null);
    }

    @Override
    public void setCurrentUserPhoneNumber(String userPhoneNumber) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PHONE_NUMBER,userPhoneNumber).apply();

    }

    @Override
    public String getCurrentUserPhoneNumber() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PHONE_NUMBER,null);
    }

    @Override
    public void setCollectorPhoneNo(String collectorPhoneNo) {
        mPrefs.edit().putString(PREF_KEY_COLLECTOR_PHONE_NUMBER,collectorPhoneNo).apply();
    }

    @Override
    public String getCollectorPhoneNo() {
        return mPrefs.getString(PREF_KEY_COLLECTOR_PHONE_NUMBER,null);
    }

    @Override
    public void setCollectorEmail(String collectorEmail) {
        mPrefs.edit().putString(PREF_KEY_COLLECTOR_EMAIL,collectorEmail).apply();

    }

    @Override
    public String getCollectorEmail() {
        return mPrefs.getString(PREF_KEY_COLLECTOR_EMAIL,null);
    }

    @Override
    public void setTimeOnStop(long currentTimeOnStop) {
        mPrefs.edit().putLong(PREF_KEY_TIME_ON_STOP,currentTimeOnStop).apply();
    }

    @Override
    public long getTimeOnStop() {
        return mPrefs.getLong(PREF_KEY_TIME_ON_STOP,0);
    }

    @Override
    public void setShopifyId(String shopifyId) {
        mPrefs.edit().putString(PREF_KEY_SHOPIFY_ID, shopifyId).apply();
    }

    @Override
    public String getShopifyId() {
        return mPrefs.getString(PREF_KEY_SHOPIFY_ID, null);
    }

    @Override
    public void setChurnDetails(List<ChurnDetailsData> churnDetails) {
        Gson gson = new Gson();
        String json = gson.toJson(churnDetails);
        mPrefs.edit().putString(PREF_KEY_CHURN_DETAILS, json).apply();
    }

    @Override
    public List<ChurnDetailsData> getChurnDetails() {
        Gson gson = new Gson();
        String json = mPrefs.getString(PREF_KEY_CHURN_DETAILS, null);
        Type type = new TypeToken<List<ChurnDetailsData>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @Override
    public void deleteChurnDetails(List<ChurnDetailsData> churnDetailsData) {
        mPrefs.edit().remove("PREF_KEY_CHURN_DETAILS").apply();
    }

    @Override
    public void setOfflineFarmerId(String offlineFarmerId) {
        mPrefs.edit().putString(PREF_KEY_OFFLINE_FARMER_ID,offlineFarmerId).apply();
    }

    @Override
    public String getOfflineFarmerId() {
        return mPrefs.getString(PREF_KEY_OFFLINE_FARMER_ID,null);
    }

    @Override
    public void setRejectionVolumee(String rejectionVolume) {
        mPrefs.edit().putString(PREF_KEY_REJECTED_VOLUME,rejectionVolume).apply();
    }

    @Override
    public String getRejectedVolumee() {
        return mPrefs.getString(PREF_KEY_REJECTED_VOLUME,null);
    }

}



