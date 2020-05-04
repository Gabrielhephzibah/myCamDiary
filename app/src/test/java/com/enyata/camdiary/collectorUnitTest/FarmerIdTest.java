package com.enyata.camdiary.collectorUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.Details;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerId.FarmerIdActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class FarmerIdTest {
    private FarmerIdActivity farmerIdActivity;
    private  ImageView back;
    private DetailsResponse detailsResponse;
    private  Details details;

    @Before
    public void setUp() throws Exception {
        farmerIdActivity = Robolectric.buildActivity(FarmerIdActivity.class)
                .create()
                .resume()
                .get();
        detailsResponse = new DetailsResponse();
        details = new Details();
        details = new Details();
    }

    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(farmerIdActivity);
    }

    @Test
    public  void testapiResponseTest() throws  Exception {
        farmerIdActivity.onResponse(detailsResponse);
        detailsResponse.getData();
        ShadowActivity shadowActivity = shadowOf(farmerIdActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(FarmerDetailsActivity.class.getName()));

    }

    @Test
     public void  backIntentTest() throws  Exception{
        back = farmerIdActivity.findViewById(R.id.back);
        back.performClick();
        Intent expected = new Intent(farmerIdActivity, BarcodeActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(farmerIdActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));
    }

}
