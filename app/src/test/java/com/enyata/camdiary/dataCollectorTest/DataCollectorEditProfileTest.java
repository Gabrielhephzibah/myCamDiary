package com.enyata.camdiary.dataCollectorTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.CollectorDetailsResponse;
import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorEditProfile.DataCollectorEditProfileActivity;

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
public class DataCollectorEditProfileTest {
    private DataCollectorEditProfileActivity dataCollectorEditProfileActivity;
    private ResetPasswordResponse resetPasswordResponse;
    private CollectorDetailsResponse collectorDetailsResponse;
    ImageView back;


    @Before
    public void  setUp() throws  Exception {
        dataCollectorEditProfileActivity = Robolectric.buildActivity(DataCollectorEditProfileActivity.class)
                .create()
                .resume()
                .get();
        resetPasswordResponse = new ResetPasswordResponse();
        collectorDetailsResponse = new CollectorDetailsResponse();
    }

    @Test
    public  void  ActivityNotNull() throws  Exception {
        assertNotNull(dataCollectorEditProfileActivity);
    }

    @Test
    public void testChangePasswordResponse() throws  Exception {
        dataCollectorEditProfileActivity.onResponse(resetPasswordResponse);

    }

    @Test
    public  void  testEditProfilePassword() throws  Exception {
        dataCollectorEditProfileActivity.onEditProfile(collectorDetailsResponse);

    }

    @Test
    public  void  testBackIntent() throws  Exception {
        back = dataCollectorEditProfileActivity.findViewById(R.id.back);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(dataCollectorEditProfileActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DataCollectorDashboardActivity.class.getName()));
    }
}
