

package com.enyata.camdiary.data.local.prefs;

import android.view.View;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.AggregationSavedCollection;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.api.request.AggregationCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel Hephzibah and Sanni Michael on 10/12/2019
 */

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    int getCurrentUserLoggedInMode();

    String getLoggedInView();

    void setLoggedInView(String type);

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String profilePicUrl);

    String getFarmerId();

    void setFarmerId(String id);

     String getCollectorId();

     void setCollectorId(String id);

     String getCollectorCollectionId();

     void setAggregationCollection(String collection);

     String getAggregationCollection();

     void  setCollectorCollectionId(String id);

    void setCollectorName(String name);

    String getCollectorName();

    void setOrderId(String orderId);

    String getOrderId();

    void setUserType(String user);

    String getUserType();

    void setCustomerName(String name);

    String getCustomerName();

    void saveAggregationCollectionList(List<AggregationSavedCollection> list);

    List<AggregationSavedCollection> getAggregationCollectionList();

    void setUserImageUrl(String image_url);

    String  getUserImageUrl();

    void  setFarmerVerificationId(String farmerVerificationId);

    String getFarmerVerificationId();

    void  setCollectorVerificationId(String collectorVerificationId);

    String getCollectorVerificationId();

    void setFarmerName(String name);

    String getFramerName();

    void setFarmerPhoneNumber(String phoneNumber);

    String getFarmerPhoneNumber();

    void setFarmerCooperativeName(String cooperativeName);

    String getFarmerCooperativeName();

    void setCurrentUserLastName(String lastName);

    String getCurrentUserLatName();

    void  setCurrentUserType(String userType);

    String getCurrentUserType();

    void  setCurrentUserAddress(String userAddress);

    String getCurrentUserAddress();

    void setCurrentUserPhoneNumber(String userPhoneNumber);

    String getCurrentUserPhoneNumber();

    void setCollectorPhoneNo( String collectorPhoneNo);

    String  getCollectorPhoneNo();

    void setCollectorEmail(String collectorEmail);

    String getCollectorEmail();

    void setTimeOnStop(long currentTimeOnStop);

    long getTimeOnStop();

    void setShopifyId(String shopifyId);

    String getShopifyId();

     void setChurnDetails(List<ChurnDetailsData> churnDetails);

     List<ChurnDetailsData>getChurnDetails();

     void deleteChurnDetails(List<ChurnDetailsData>churnDetailsData);

     void setOfflineFarmerId(String  offlineFarmerId);

     String getOfflineFarmerId();

     void setRejectionVolumee(String  rejectionVolume);

     String  getRejectedVolumee();













}

