package com.enyata.camdiary.aggregatorUnitTest;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.aggregations.barcode.collectorID.CollectorIdActivity;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerId.FarmerIdActivity;
import com.enyata.camdiary.ui.scanbarcode.aggregatorScanBarCode.AggregatorScanBarCode;
import com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode.CollectorScanBarCode;

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
public class AggregatorScanBarcode {
    private ScanActivity scanActivity;
    private Button scanBarcode;
    private TextView enterFarmerId;
    private ImageView back;

    @Before
    public  void  setUp() throws  Exception {
        scanActivity = Robolectric.buildActivity(ScanActivity.class)
                .create()
                .resume()
                .get();

    }

    @Test
    public  void activityNotNull() throws  Exception {
        assertNotNull(scanActivity);
    }

    @Test
    public void testScanBarcodeIntent() throws  Exception {
        scanBarcode = scanActivity.findViewById(R.id.scan);
        scanBarcode.performClick();
        Intent expected = new Intent(scanActivity,  AggregatorScanBarCode.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(scanActivity);
        Intent actual =  shadowActivity.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));

    }

    @Test
    public  void  testEnterFarmerIdIntent() throws Exception {
        enterFarmerId = scanActivity.findViewById(R.id.enterCollector_id);
        enterFarmerId.performClick();
        ShadowActivity shadowActivity = shadowOf(scanActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(CollectorIdActivity.class.getName()));

    }

    @Test
    public  void  testBackIntent() throws  Exception {
        back = scanActivity.findViewById(R.id.back);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(scanActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(AggregatorDashboardActivity.class.getName()));

    }

}
