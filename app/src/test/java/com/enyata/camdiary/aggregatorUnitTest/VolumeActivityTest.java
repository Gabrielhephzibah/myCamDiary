package com.enyata.camdiary.aggregatorUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeActivity;
import com.enyata.camdiary.ui.aggregations.product.ProductActivity;

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
public class VolumeActivityTest {
    private VolumeActivity volumeActivity;
    ImageView back;

    @Before
    public  void  setUp() throws  Exception{
        volumeActivity = Robolectric.buildActivity(VolumeActivity.class)
                .create()
                .resume()
                .get();
    }

    @Test
    public  void  activityNotNull() throws  Exception{
        assertNotNull(volumeActivity);
    }

    @Test
    public  void  testBackIntent() throws  Exception{
        back = volumeActivity.findViewById(R.id.back);
        back.performClick();
        Intent expected = new Intent(volumeActivity, ProductActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(volumeActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));
    }


}
