package com.enyata.camdiary.deliveryUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.BottleInventoryResponse;
import com.enyata.camdiary.data.model.api.response.DeliveryCompletedResponse;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;
import com.enyata.camdiary.data.model.api.response.PendingDeliveryResponse;
import com.enyata.camdiary.ui.aggregations.aggregatorEditProfile.AggregatorEditProfileActivity;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveryEditProfile.DeliveryEditProfileActivity;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;
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
public class DeliveryDashboardTest {
    private DeliveryDashboardActivity deliveryDashboardActivity;
    private DeliveryCompletedResponse deliveryCompletedResponse;
    private PendingDeliveryResponse pendingDeliveryResponse;
    private BottleInventoryResponse bottleInventoryResponse;
    ImageView logOut,signup, history;
    CardView profilePicture;

    @Before
    public  void setUp() throws  Exception {
        deliveryDashboardActivity = Robolectric.buildActivity(DeliveryDashboardActivity.class)
                .create()
                .resume()
                .get();
        deliveryCompletedResponse = new DeliveryCompletedResponse();
        pendingDeliveryResponse = new PendingDeliveryResponse();
        bottleInventoryResponse = new BottleInventoryResponse();
    }

    @Test
    public  void  activityNotNull() throws  Exception {
        assertNotNull(deliveryDashboardActivity);
    }

    @Test
    public  void testCreateSliderDash() throws  Exception {
        deliveryDashboardActivity.createSliderDash(0);
    }

    @Test
    public  void testPendingDeliveryResponse() throws  Exception {
        deliveryDashboardActivity.getPendingDelivery(pendingDeliveryResponse);
    }

    @Test
    public  void  testDeliveriesCompletedResponse() throws  Exception {
        deliveryDashboardActivity.getDeliveryCompleted(deliveryCompletedResponse);
    }

    @Test
    public  void  testNoOfCollectorResponse() throws  Exception{
        deliveryDashboardActivity.getBottleInventory(bottleInventoryResponse);
    }

    @Test
    public  void testSignupIntent() throws  Exception {
        signup = deliveryDashboardActivity.findViewById(R.id.signup);
        signup.performClick();
        ShadowActivity shadowActivity = shadowOf(deliveryDashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(SignupActivity.class.getName()));

    }

    @Test
    public  void  testHistoryIntent() throws  Exception{
        history = deliveryDashboardActivity.findViewById(R.id.history);
        history.performClick();
        Intent expected = new Intent(deliveryDashboardActivity,  DeliveryHistoryActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(deliveryDashboardActivity);
        Intent actual =  shadowActivity.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));

    }

    @Test
    public  void  testLogoutIntent() throws  Exception{
        logOut = deliveryDashboardActivity.findViewById(R.id.logout);
        logOut.performClick();
        ShadowActivity shadowActivity = shadowOf(deliveryDashboardActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(LoginActivity.class.getName()));

    }

    @Test
    public  void  testProfilePictureIntent() throws  Exception{
        profilePicture = deliveryDashboardActivity.findViewById(R.id.circle);
        profilePicture.performClick();
        Intent expected = new Intent(deliveryDashboardActivity, DeliveryEditProfileActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(deliveryDashboardActivity);
        Intent actual =  shadowActivity.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));

    }
}
