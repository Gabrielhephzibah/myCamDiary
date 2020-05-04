package com.enyata.camdiary.aggregatorUnitTest;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.CollectorDetails;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.aggregations.milkcollection.MilkCollectionActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
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
public class CollectorDetailTest {
    private CollectorDetailActivity collectorDetailActivity;
    ImageView back;
    Button proceed;


    @Before
    public void setUp(){
        collectorDetailActivity = Robolectric.buildActivity(CollectorDetailActivity.class)
                .create()
                .resume()
                .get();
    }


    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(collectorDetailActivity);
    }

    @Test
    public void testBackIntent() throws  Exception {
        back = collectorDetailActivity.findViewById(R.id.back);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(collectorDetailActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(ScanActivity.class.getName()));
    }

    @Test
    public  void  testProceedIntent() throws  Exception {
        proceed = collectorDetailActivity.findViewById(R.id.proceed);
        proceed.performClick();
        Intent expected = new Intent(collectorDetailActivity, MilkCollectionActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(collectorDetailActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));
    }

}
