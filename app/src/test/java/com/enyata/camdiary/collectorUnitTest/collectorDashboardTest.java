package com.enyata.camdiary.collectorUnitTest;

import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.Collection;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.remote.ApiEndPoint;
import com.enyata.camdiary.data.remote.AppApiHelper;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardNavigator;
import com.enyata.camdiary.ui.collections.dashboard.DashboardViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class collectorDashboardTest {
private  DashboardViewModel dashboardViewModel;
private AppApiHelper appApiHelper;
private TestSubscriber<Boolean> testSubscriber;
   private SchedulerProvider schedulerProvider;
   TestSubscriber<SchedulerProvider>subscribeOn;
    private TestSubscriber<List<CollectionResponse>> testSubscriberPlaces;
//
//    @Before
//    public void setUpViewModel() {
//        appApiHelper = Mockito.mock(AppApiHelper.class);
//        testSubscriber = TestSubscriber.create();
//        testSubscriberPlaces = TestSubscriber.create();
////        when(appApiHelper.getTodaysCollection()).thenReturn(testDataObservable());
//    }
//
//    private Flowable<CollectionResponse> testDataObservable() {
//        return testDataObservable();
//    }

    @Test
    public void getTodayCollection() {


    }


}
