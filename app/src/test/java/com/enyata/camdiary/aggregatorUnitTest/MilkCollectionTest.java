package com.enyata.camdiary.aggregatorUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.MilkCollectionDataResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.aggregations.milkcollection.MilkCollectionActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class MilkCollectionTest {
    private MilkCollectionActivity milkCollectionActivity;
    private MilkCollectionDataResponse milkCollectionDataResponse;
    private NewCollectionResponse newCollectionResponse;
    ImageView back;

    @Before
    public  void setUp() throws  Exception{
        milkCollectionActivity = Robolectric.buildActivity(MilkCollectionActivity.class)
                .create()
                .resume()
                .get();

        milkCollectionDataResponse = new MilkCollectionDataResponse();
        newCollectionResponse = new NewCollectionResponse();
    }

    @Test
    public  void activityNotNull() throws  Exception{
        assertNotNull(milkCollectionActivity);
    }

    @Test
    public  void testMilkCollectionResponse() throws  Exception{
        milkCollectionActivity.getMilkCollectionData(milkCollectionDataResponse);
    }

    @Test
    public  void  testCreateAggregationResponse() throws  Exception{
        milkCollectionActivity.onAggregationResponse(newCollectionResponse);
    }

    @Test
    public  void  testBckIntent() throws  Exception{
        back = milkCollectionActivity.findViewById(R.id.back);
        back.performClick();
        Intent expected = new Intent(milkCollectionActivity, CollectorDetailActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(milkCollectionActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));

    }


}
