package com.enyata.camdiary.collectorUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.CollectorDetailsResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;

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
public class CollectorEditProfileTest {
    private CollectorEditProfileActivity collectorEditProfileActivity;
    private ResetPasswordResponse resetPasswordResponse;
    private CollectorDetailsResponse collectorDetailsResponse;
    ImageView back;


    @Before
    public void  setUp() throws  Exception {
        collectorEditProfileActivity = Robolectric.buildActivity(CollectorEditProfileActivity.class)
                .create()
                .resume()
                .get();
        resetPasswordResponse = new ResetPasswordResponse();
        collectorDetailsResponse = new CollectorDetailsResponse();
    }

    @Test
    public  void  ActivityNotNull() throws  Exception {
        assertNotNull(collectorEditProfileActivity);
    }

    @Test
     public void testChangePasswordResponse() throws  Exception {
        collectorEditProfileActivity.onResponse(resetPasswordResponse);

    }

    @Test
    public  void  testEditProfilePassword() throws  Exception {
        collectorEditProfileActivity.onEditProfile(collectorDetailsResponse);

    }

    @Test
    public  void  testBackIntent() throws  Exception {
        back = collectorEditProfileActivity.findViewById(R.id.back);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(collectorEditProfileActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DashboardActivity.class.getName()));
    }
}
