package com.enyata.camdiary.collectorUnitTest;

import android.content.Intent;
import android.widget.Button;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.statusofcollection.StatusOfCollectionActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class StatusOfCollectionTest {
    private StatusOfCollectionActivity statusOfCollectionActivity;
    Button home;

  @Before
  public   void  setUp() throws  Exception{
      statusOfCollectionActivity = Robolectric.buildActivity(StatusOfCollectionActivity.class)
              .create()
              .resume()
              .get();

  }

  @Test
    public  void activityNotNull() throws  Exception {
      assertNotNull(statusOfCollectionActivity);
  }

  @Test
    public  void  testHomeIntent() throws  Exception {
      home = statusOfCollectionActivity.findViewById(R.id.back2home);
      home.performClick();
      ShadowActivity shadowActivity = shadowOf(statusOfCollectionActivity);
      Intent startIntent = shadowActivity.getNextStartedActivity();
      ShadowIntent shadowIntent = shadowOf(startIntent);
      assertThat(shadowIntent.getIntentClass().getName(), equalTo(DashboardActivity.class.getName()));
  }




}
