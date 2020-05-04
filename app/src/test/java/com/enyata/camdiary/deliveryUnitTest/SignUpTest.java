package com.enyata.camdiary.deliveryUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmsuccess.SignsuccessActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;

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
public class SignUpTest {
    private SignupActivity signupActivity;
    ImageView back;
    private DispatcherSignUpResponse dispatcherSignUpResponse;

    @Before
    public void setUp(){
        signupActivity = Robolectric.buildActivity(SignupActivity.class)
                .create()
                .resume()
                .get();
        dispatcherSignUpResponse = new DispatcherSignUpResponse();
    }


    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(signupActivity);
    }

    @Test
    public void testBackIntent() throws  Exception {
        back = signupActivity.findViewById(R.id.back2home);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(signupActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DeliveryDashboardActivity.class.getName()));
    }

    @Test
    public  void  testDispatcherSignUp() throws  Exception {
        signupActivity.dispatcherSignup(dispatcherSignUpResponse);
        ShadowActivity shadowActivity = shadowOf(signupActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(SignsuccessActivity.class.getName()));
    }
}
