package com.enyata.camdiary.deliveryUnitTest;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.robolectric.Shadows.shadowOf;
@RunWith(RobolectricTestRunner.class)
public class FinishTest {
    private FinishActivity finishActivity;
    Button  finish;


    @Before
    public void setUp(){
        finishActivity = Robolectric.buildActivity(FinishActivity.class)
                .create()
                .resume()
                .get();

    }


    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(finishActivity);
    }

    @Test
    public void testFinishIntent() throws  Exception {
        finish = finishActivity.findViewById(R.id.finish);
        finish.performClick();
        ShadowActivity shadowActivity = shadowOf(finishActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DeliveryDashboardActivity.class.getName()));
    }

}