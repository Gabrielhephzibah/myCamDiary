package com.enyata.camdiary.aggregatorUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.ui.aggregations.aggregatorEditProfile.AggregatorEditProfileActivity;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode.CollectorScanBarCode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class AggregatorDashboardTest {
    private AggregatorDashboardActivity aggregatorDashboardActivity;
    private AggregationCollectionResponse aggregationCollectionResponse;
    private AggregationVolume aggregationVolume;
    private NumberOfCollectors numberOfCollectors;
    ImageView logOut, scanBarCode, history;
    CardView profilePicture;

    @Before
    public  void setUp() throws  Exception {
        aggregatorDashboardActivity = Robolectric.buildActivity(AggregatorDashboardActivity.class)
                .create()
                .resume()
                .get();
        aggregationCollectionResponse = new AggregationCollectionResponse();
        aggregationVolume = new AggregationVolume();
        numberOfCollectors = new NumberOfCollectors();
    }

    @Test
    public  void  activityNotNull() throws  Exception {
        assertNotNull(aggregatorDashboardActivity);
    }

    @Test
    public  void testCreateSliderDash() throws  Exception {
        aggregatorDashboardActivity.createSliderDash(0);
    }

    @Test
    public  void testTodayAggregationResponse() throws  Exception {
        aggregatorDashboardActivity. getAggregatorTodayCollection(aggregationCollectionResponse);
    }

    @Test
    public  void  testAggregationVolumeResponse() throws  Exception {
        aggregatorDashboardActivity.displayAggregatorVolume(aggregationVolume);
    }

    @Test
    public  void  testNoOfCollectorResponse() throws  Exception{
        aggregatorDashboardActivity.numberOfCollectors(numberOfCollectors);
    }

    @Test
    public  void testBarCodeIntent() throws  Exception {
        scanBarCode = aggregatorDashboardActivity.findViewById(R.id.scanbarcode);
        scanBarCode.performClick();
        ShadowActivity shadowActivity = shadowOf(aggregatorDashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(ScanActivity.class.getName()));

    }

    @Test
    public  void  testHistoryIntent() throws  Exception{
        history = aggregatorDashboardActivity.findViewById(R.id.history);
        history.performClick();
        Intent expected = new Intent(aggregatorDashboardActivity,  AggregatorHIstoryActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(aggregatorDashboardActivity);
        Intent actual =  shadowActivity.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));

    }

    @Test
    public  void  testLogoutIntent() throws  Exception{
        logOut = aggregatorDashboardActivity.findViewById(R.id.logOut);
        logOut.performClick();
        ShadowActivity shadowActivity = shadowOf(aggregatorDashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(LoginActivity.class.getName()));

    }

    @Test
    public  void  testProfilePictureIntent() throws  Exception{
        profilePicture = aggregatorDashboardActivity.findViewById(R.id.circle);
        profilePicture.performClick();
        Intent expected = new Intent(aggregatorDashboardActivity, AggregatorEditProfileActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(aggregatorDashboardActivity);
        Intent actual =  shadowActivity.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));

    }





}
