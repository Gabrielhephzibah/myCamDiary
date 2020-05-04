package com.enyata.camdiary.collectorUnitTest;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerId.FarmerIdActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode.CollectorScanBarCode;
import com.google.android.gms.vision.barcode.Barcode;

import org.apache.tools.ant.taskdefs.EchoXML;
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
public class CollectorBarcodeTest {
    private BarcodeActivity barcodeActivity;
   private Button scanBarcode;
   private TextView enterFarmerId;
   private ImageView back;

    @Before
    public  void  setUp() throws  Exception {
        barcodeActivity = Robolectric.buildActivity(BarcodeActivity.class)
                .create()
                .resume()
                .get();

    }

    @Test
    public  void activityNotNull() throws  Exception {
        assertNotNull(barcodeActivity);
    }

    @Test
    public void testScanBarcodeIntent() throws  Exception {
        scanBarcode = barcodeActivity.findViewById(R.id.scan);
        scanBarcode.performClick();
        Intent expected = new Intent(barcodeActivity,  CollectorScanBarCode.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(barcodeActivity);
        Intent actual =  shadowActivity.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));

    }

    @Test
    public  void  testEnterFarmerIdIntent() throws Exception {
        enterFarmerId = barcodeActivity.findViewById(R.id.enterFarmer_id);
        enterFarmerId.performClick();
        ShadowActivity shadowActivity = shadowOf(barcodeActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(FarmerIdActivity.class.getName()));

    }

    @Test
    public  void  testBackIntent() throws  Exception {
        back = barcodeActivity.findViewById(R.id.back);
        back.performClick();
        ShadowActivity shadowActivity = shadowOf(barcodeActivity);
        Intent startIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startIntent);
        assertThat(shadowIntent.getIntentClass().getName(), equalTo(DashboardActivity.class.getName()));

    }





}
