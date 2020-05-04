package com.enyata.camdiary.collectorUnitTest;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;

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
public class FarmerDetailsTest {
    private FarmerDetailsActivity farmerDetailsActivity;
    ImageView back;
    Button proceed;


    @Before
    public void setUp(){
        farmerDetailsActivity = Robolectric.buildActivity(FarmerDetailsActivity.class)
                .create()
                .resume()
                .get();
    }


    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(farmerDetailsActivity);
    }

    @Test
    public void testBackIntent() throws  Exception {
        back = farmerDetailsActivity.findViewById(R.id.back);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(farmerDetailsActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo( BarcodeActivity.class.getName()));
    }

    @Test
    public  void  testProceedIntent() throws  Exception {
        proceed = farmerDetailsActivity.findViewById(R.id.proceed);
        proceed.performClick();
        Intent expected = new Intent(farmerDetailsActivity, EnterVolumeActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(farmerDetailsActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));
    }

}
