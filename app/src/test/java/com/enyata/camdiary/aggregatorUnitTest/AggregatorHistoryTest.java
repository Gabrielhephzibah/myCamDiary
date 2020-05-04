package com.enyata.camdiary.aggregatorUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.AggregationHistoryResponse;
import com.enyata.camdiary.ui.aggregations.barcode.collectorID.CollectorIdActivity;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.login.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class AggregatorHistoryTest {
    private AggregatorHIstoryActivity aggregatorHIstoryActivity;
    ImageView scanBarCode, logOut, back;
    AggregationHistoryResponse aggregationHistoryResponse;

@Before
    public  void setUp() throws  Exception{
        aggregatorHIstoryActivity = Robolectric.buildActivity(AggregatorHIstoryActivity.class)
                .create()
                .resume()
                .get();
        aggregationHistoryResponse = new AggregationHistoryResponse();
    }
    @Test
    public  void activityNotNull() throws  Exception{
        assertNotNull(aggregatorHIstoryActivity);

    }

    @Test
    public void  testAggregationHistoryResponse(){
        aggregatorHIstoryActivity.getAggregationHistory(aggregationHistoryResponse);
    }

    @Test
    public  void  testScanBarcodeIntent() throws  Exception{
        scanBarCode = aggregatorHIstoryActivity.findViewById(R.id.scanbarcode);
        scanBarCode.performClick();
        ShadowActivity shadowActivity = shadowOf(aggregatorHIstoryActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(ScanActivity.class.getName()));

    }

    @Test
    public  void  testLogoutIntent() throws  Exception{
        logOut = aggregatorHIstoryActivity.findViewById(R.id.logOut);
        logOut.performClick();
        ShadowActivity shadowActivity = shadowOf(aggregatorHIstoryActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(LoginActivity.class.getName()));

    }

    @Test
    public  void  testBackIntent() throws  Exception {
        back = aggregatorHIstoryActivity.findViewById(R.id.back);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(aggregatorHIstoryActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(AggregatorDashboardActivity.class.getName()));

    }

}
