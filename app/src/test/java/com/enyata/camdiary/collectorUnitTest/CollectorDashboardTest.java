package com.enyata.camdiary.collectorUnitTest;

import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.enyata.camdiary.BuildConfig;
import com.enyata.camdiary.R;
import com.enyata.camdiary.data.AppDataManager;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.data.remote.ApiEndPoint;
import com.enyata.camdiary.data.remote.ApiHeader;
import com.enyata.camdiary.data.remote.ApiHelper;
import com.enyata.camdiary.data.remote.AppApiHelper;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardNavigator;
import com.enyata.camdiary.ui.collections.dashboard.DashboardViewModel;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.utils.rx.SchedulerProvider;
import com.squareup.picasso.Picasso;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.Config;
import org.robolectric.shadow.api.Shadow;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
//@Config(sdk = Build.VERSION_CODES.P)
public class CollectorDashboardTest {
    private DashboardActivity dashboardActivity;
    private  VolumeResponse response;
    private  AllEntries allEntries;
    private CollectionResponse todayCollectionResponse;
    ImageView barCode,history,dataCollection,logOut;
    CardView profilePicture;


    @Before
    public void setUp() throws Exception {
        dashboardActivity = Robolectric.buildActivity(DashboardActivity.class)
                .create()
                .resume()
                .get();
        todayCollectionResponse = new CollectionResponse();
        response = new VolumeResponse();
        allEntries = new AllEntries();

    }

    @Test
  public void shouldNotBeNull() throws Exception{
   assertNotNull(dashboardActivity);

}

    @Test
    public void createSliderDashTest() throws Exception {
        int expected = 0;
        dashboardActivity.createSliderDash(expected);
    }


    @Test
    public void getAcceptedVolumeTest() throws Exception {
        dashboardActivity.displayAcceptedVolume(response);

    }


    @Test
    public void getRejectedVolumeTest() throws  Exception{
        dashboardActivity.displayRejectedVolume(response);

    }

    @Test
    public  void  getAllEntriessTest() throws  Exception{
        dashboardActivity.getAllEntries(allEntries);
    }

    @Test
    public  void  getCollectionTest() throws  Exception {
        dashboardActivity.getTodayCollection(todayCollectionResponse);
    }

    @Test
    public  void scanBarcodeIntentTest() throws  Exception {
         barCode = dashboardActivity.findViewById(R.id.scanbarcodeIcon);
         barCode.performClick();
        Intent expectedIntent = new Intent(dashboardActivity, BarcodeActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(dashboardActivity);
        Intent actualIntent =  shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
//        ImageView imageView = dashboardActivity.findViewById(R.id.historyIcon);
//        imageView.performClick();
//        ShadowActivity shadowActivity = shadowOf(dashboardActivity);
//        Intent startIntent  = shadowActivity.getNextStartedActivity();
//        ShadowIntent shadowIntent = shadowOf(startIntent);
//        assertThat(shadowIntent.getIntentClass().getName(),equalTo(HistoryActivity.class.getName()));

    }
    @Test
    public  void HistoryIntentTest() throws Exception{
        history = dashboardActivity.findViewById(R.id.historyIcon);
        history.performClick();
        ShadowActivity shadowActivity = shadowOf(dashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(),equalTo(HistoryActivity.class.getName()));

    }

    @Test
    public void dataCollectionIntentTest() throws  Exception{
        dataCollection = dashboardActivity.findViewById(R.id.data_collection);
        dataCollection.performClick();
        Intent expected = new Intent( dashboardActivity, DataCollectionActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(dashboardActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));
    }

    @Test
    public  void logOutIntentTest() throws  Exception {
        logOut = dashboardActivity.findViewById(R.id.logout);
        logOut.performClick();
        ShadowActivity shadowActivity = shadowOf(dashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(LoginActivity.class.getName()));

    }

    @Test
    public void profilePictureIntentTest() throws  Exception {
        profilePicture = dashboardActivity.findViewById(R.id.circle);
        profilePicture.performClick();
        Intent expected = new Intent( dashboardActivity, CollectorEditProfileActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(dashboardActivity);
        Intent actual =  shadowActivity.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));
    }






}
