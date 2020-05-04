package com.enyata.camdiary.collectorUnitTest;

import android.content.Intent;
import android.widget.ImageView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.rejection.reason.ReasonActivity;

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
public class ReasonTest {

    private ReasonActivity reasonActivity;
    ImageView back;
    private NewCollectionResponse newCollectionResponse;

    @Before
    public  void  setUp() throws  Exception {
        reasonActivity = Robolectric.buildActivity(ReasonActivity.class)
                .create()
                .resume()
                .get();

        newCollectionResponse = new NewCollectionResponse();


    }

    @Test
    public  void  activityNotNull() throws  Exception {
        assertNotNull(reasonActivity);
    }

    @Test
    public  void testBackIntent() throws  Exception {
        back = reasonActivity.findViewById(R.id.back);
        back.performClick();
        Intent expected = new Intent(reasonActivity, EnterVolumeActivity.class);
        ShadowActivity activityShadow = Shadows.shadowOf(reasonActivity);
        Intent actual = activityShadow.getNextStartedActivity();
        assertTrue(actual.filterEquals(expected));

    }

    @Test
    public  void  testResponse() throws  Exception {
        reasonActivity.onResponse(newCollectionResponse);
    }


}
