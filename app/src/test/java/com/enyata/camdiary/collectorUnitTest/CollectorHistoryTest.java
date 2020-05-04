package com.enyata.camdiary.collectorUnitTest;

import android.content.Intent;
import android.os.Build;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.CollectionHistoryResponse;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;
import com.enyata.camdiary.ui.login.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
//@Config(sdk = Build.VERSION_CODES.P)
public class CollectorHistoryTest {
    private HistoryActivity historyActivity;
    private CollectionHistoryResponse collectionHistoryResponse;
    ImageView datacollection, scanBarcode, logout,back;


    @Before
    public  void  setUp()throws  Exception {
        historyActivity = Robolectric.buildActivity(HistoryActivity.class)
                .create()
                .resume()
                .get();
        collectionHistoryResponse = new CollectionHistoryResponse();
    }

    @Test
    public  void  activityNotNull() throws  Exception {
        assertNotNull(historyActivity);
    }

    @Test
    public void testCollectionHistory() throws  Exception{
        historyActivity.getCollectionHistory(collectionHistoryResponse);
    }

    @Test
    public  void  testDataCollectionIntent() throws  Exception {
        datacollection = historyActivity.findViewById(R.id.data_collection);
        datacollection.performClick();
        ShadowActivity shadowActivity = shadowOf(historyActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo( DataCollectionActivity.class.getName()));

    }

    @Test
    public  void  testScanBarcodeIntent() throws  Exception {
        scanBarcode = historyActivity.findViewById(R.id.scanbarcode);
        scanBarcode.performClick();
        ShadowActivity shadowActivity = shadowOf(historyActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(BarcodeActivity.class.getName()));

    }

    @Test
    public  void  testLogOutIntent() throws  Exception {
        logout = historyActivity.findViewById(R.id.logout);
        logout.performClick();
        Intent expected = new Intent(historyActivity, LoginActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(historyActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));

    }

    @Test
    public  void  testBackIntent() throws  Exception{
        back = historyActivity.findViewById(R.id.back);
        back.performClick();
        Intent expected = new Intent(historyActivity, DashboardActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(historyActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));
    }



}
