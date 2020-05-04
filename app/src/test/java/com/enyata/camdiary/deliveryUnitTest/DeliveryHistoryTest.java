package com.enyata.camdiary.deliveryUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.AggregationHistoryResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryHistoryResponseData;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;
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
public class DeliveryHistoryTest {
    private DeliveryHistoryActivity deliveryHistoryActivity;
    ImageView signUp, logOut, back;
    DeliveryHistoryResponseData deliveryHistoryResponseData;

    @Before
    public  void setUp() throws  Exception{
        deliveryHistoryActivity = Robolectric.buildActivity(DeliveryHistoryActivity.class)
                .create()
                .resume()
                .get();
        deliveryHistoryResponseData = new DeliveryHistoryResponseData();
    }
    @Test
    public  void activityNotNull() throws  Exception{
        assertNotNull(deliveryHistoryActivity);

    }

    @Test
    public void  testAggregationHistoryResponse(){
        deliveryHistoryActivity.deliveryHistory(deliveryHistoryResponseData);
    }

    @Test
    public  void  testSignUpIntent() throws  Exception{
        signUp = deliveryHistoryActivity.findViewById(R.id.signup);
        signUp.performClick();
        ShadowActivity shadowActivity = shadowOf(deliveryHistoryActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(SignupActivity.class.getName()));

    }

    @Test
    public  void  testLogoutIntent() throws  Exception{
        logOut = deliveryHistoryActivity.findViewById(R.id.logout);
        logOut.performClick();
        ShadowActivity shadowActivity = shadowOf(deliveryHistoryActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(LoginActivity.class.getName()));

    }

    @Test
    public  void  testBackIntent() throws  Exception {
        back = deliveryHistoryActivity.findViewById(R.id.back2home);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(deliveryHistoryActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DeliveryDashboardActivity.class.getName()));

    }

}
