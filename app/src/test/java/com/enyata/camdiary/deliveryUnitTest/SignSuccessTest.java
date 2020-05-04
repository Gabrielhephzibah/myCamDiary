package com.enyata.camdiary.deliveryUnitTest;

import android.content.Intent;
import android.widget.Button;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmsuccess.SignsuccessActivity;

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
public class SignSuccessTest {
    private SignsuccessActivity signsuccessActivity;
    Button home;


    @Before
    public void setUp(){
        signsuccessActivity = Robolectric.buildActivity(SignsuccessActivity.class)
                .create()
                .resume()
                .get();

    }


    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(signsuccessActivity);
    }

    @Test
    public void testFinishIntent() throws  Exception {
        home = signsuccessActivity.findViewById(R.id.home);
        home.performClick();
        ShadowActivity shadowActivity = shadowOf(signsuccessActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DeliveryDashboardActivity.class.getName()));
    }
}
