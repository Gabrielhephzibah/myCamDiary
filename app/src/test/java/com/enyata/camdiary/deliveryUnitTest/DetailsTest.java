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
public class DetailsTest {
    private DetailsActivity detailsActivity;
    ImageView back;
    Button deliver;


    @Before
    public void setUp(){
        detailsActivity = Robolectric.buildActivity(DetailsActivity.class)
                .create()
                .resume()
                .get();

    }


    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(detailsActivity);
    }

    @Test
    public void testBackIntent() throws  Exception {
        back = detailsActivity.findViewById(R.id.back);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(detailsActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DeliveryDashboardActivity.class.getName()));
    }

    @Test
    public  void  testDispatcherSignUp() throws  Exception {
        deliver = detailsActivity.findViewById(R.id.deliverButton);
        deliver.performClick();
        ShadowActivity shadowActivity = shadowOf(detailsActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(BottlesActivity.class.getName()));
    }
}
