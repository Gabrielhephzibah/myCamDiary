package com.enyata.camdiary.aggregatorUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.CollectorDetailsResponse;
import com.enyata.camdiary.data.model.api.response.Details;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.ui.aggregations.barcode.collectorID.CollectorIdActivity;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
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
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class CollectorIdTest {
    private CollectorIdActivity collectorIdActivity;
    private ImageView back;
    private CollectorDetailsResponse response;
    private Details details;

    @Before
    public void setUp() throws Exception {
        collectorIdActivity = Robolectric.buildActivity(CollectorIdActivity.class)
                .create()
                .resume()
                .get();
        response   = new CollectorDetailsResponse();
        details = new Details();
        details = new Details();
    }

    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(collectorIdActivity);
    }

    @Test
    public  void testgetCollectorDetails() throws  Exception {
//        collectorIdActivity.getCollectorDetails(response);
//        ShadowActivity shadowActivity = shadowOf(collectorIdActivity);
//        Intent startIntent = shadowActivity.getNextStartedActivity();
//        ShadowIntent shadowIntent = shadowOf(startIntent);
//        assertThat(shadowIntent.getIntentClass().getName(), equalTo(CollectorDetailActivity.class.getName()));

    }

    @Test
    public void  backIntentTest() throws  Exception{
        back = collectorIdActivity.findViewById(R.id.back);
        back.performClick();
        Intent expected = new Intent(collectorIdActivity, ScanActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(collectorIdActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));
    }

}
