package com.enyata.camdiary.dataCollectorTest;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.data.bdsData.BdsDataActivity;
import com.enyata.camdiary.ui.collections.data.cdsData.CdsDataActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.data.dataScanBarcode.DataScanCodeActivity;
import com.enyata.camdiary.ui.collections.data.pdsData.PdsDataActivity;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;
import com.enyata.camdiary.ui.login.LoginActivity;

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
public class DataCollectorDashboardTest {
    private DataCollectorDashboardActivity dataCollectorDashboardActivity;
    Button cds, bds, pds;
    ImageView logout;


    @Before
    public void setUp() throws Exception {
        dataCollectorDashboardActivity = Robolectric.buildActivity(DataCollectorDashboardActivity.class)
                .create()
                .resume()
                .get();


    }

    @Test
    public void activityNotBeNull() throws Exception {
        assertNotNull(dataCollectorDashboardActivity);

    }

    @Test
    public void testCdsIntent() throws Exception {
        cds = dataCollectorDashboardActivity.findViewById(R.id.cds);
        cds.performClick();
        ShadowActivity shadowActivity = shadowOf(dataCollectorDashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(CdsDataActivity.class.getName()));

    }


    @Test
    public void testBdsIntent() throws Exception {
        bds = dataCollectorDashboardActivity.findViewById(R.id.bds);
        bds.performClick();
        ShadowActivity shadowActivity = shadowOf(dataCollectorDashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(BdsDataActivity.class.getName()));

    }


    @Test
    public void testPdsIntent() throws Exception {
        pds = dataCollectorDashboardActivity.findViewById(R.id.pds);
        pds.performClick();
        ShadowActivity shadowActivity = shadowOf(dataCollectorDashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DataScanCodeActivity.class.getName()));


    }


    @Test
    public void logOutIntentTest() throws Exception {
        logout = dataCollectorDashboardActivity.findViewById(R.id.logout);
        logout.performClick();
        ShadowActivity shadowActivity = shadowOf(dataCollectorDashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(LoginActivity.class.getName()));

    }


}
