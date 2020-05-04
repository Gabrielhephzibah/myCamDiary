package com.enyata.camdiary.deliveryUnitTest;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.aggregations.milkcollection.MilkCollectionActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.robolectric.Shadows.shadowOf;
@RunWith(RobolectricTestRunner.class)
public class BottlesTest {
    private BottlesActivity bottlesActivity;
    ImageView back;
    private DispatcherSignUpResponse dispatcherSignUpResponse;

    @Before
    public void setUp(){
        bottlesActivity = Robolectric.buildActivity(BottlesActivity.class)
                .create()
                .resume()
                .get();
        dispatcherSignUpResponse = new DispatcherSignUpResponse();
    }


    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(bottlesActivity);
    }

    @Test
    public void testBackIntent() throws  Exception {
        back = bottlesActivity.findViewById(R.id.back2home);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(bottlesActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DetailsActivity.class.getName()));
    }

    @Test
    public  void  testCreateDelivery() throws  Exception {
        bottlesActivity.onResponse(dispatcherSignUpResponse);

        ShadowActivity shadowActivity = shadowOf(bottlesActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(FinishActivity.class.getName()));
    }
}
