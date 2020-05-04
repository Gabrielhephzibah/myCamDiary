package com.enyata.camdiary.collectorUnitTest;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.rejection.reason.ReasonActivity;
import com.enyata.camdiary.ui.collections.statusofcollection.StatusOfCollectionActivity;
import com.google.android.material.textfield.TextInputEditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class CollectorEnterVolumeTest {
    private EnterVolumeActivity enterVolumeActivity;
    NewCollectionResponse newCollectionResponse;
    ImageView back;
    Button rejectVolume, acceptVolume;

    TextInputEditText editText;



    @Before
    public  void  setUp() throws  Exception{
        enterVolumeActivity = Robolectric.buildActivity(EnterVolumeActivity.class)
                .create()
                .resume()
                .get();
        newCollectionResponse = new NewCollectionResponse();
    }

    @Test
    public void  activityNotNull() throws  Exception {
        assertNotNull(enterVolumeActivity);
    }

    @Test
    public void displayResponseTest() throws  Exception {
        enterVolumeActivity.displayResponse(newCollectionResponse);
        ShadowActivity shadowActivity = shadowOf(enterVolumeActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo( StatusOfCollectionActivity.class.getName()));
    }

    @Test
    public void testBackIntent() throws  Exception {
        back = enterVolumeActivity.findViewById(R.id.back);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(enterVolumeActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(FarmerDetailsActivity.class.getName()));
    }

    @Test
    public void  testRejectVolumeButton() throws  Exception {
        rejectVolume = enterVolumeActivity.findViewById(R.id.rejectVolume);
        editText = enterVolumeActivity.findViewById(R.id.volumeEditText);
        rejectVolume.performClick();
        if (editText.getText().toString().isEmpty()){
            return;
        }else {
            ShadowActivity shadowActivity = shadowOf(enterVolumeActivity);
            Intent startIntent = shadowActivity.getNextStartedActivity();
            ShadowIntent shadowIntent = shadowOf(startIntent);
            assertThat(shadowIntent.getIntentClass().getName(), equalTo(ReasonActivity.class.getName()));

        }

    }

    @Test
    public  void testAcceptVolumeButton() throws  Exception {
        acceptVolume = enterVolumeActivity.findViewById(R.id.acceptVolume);
        acceptVolume.performClick();
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
//        dialog.getButton()










    }




}
