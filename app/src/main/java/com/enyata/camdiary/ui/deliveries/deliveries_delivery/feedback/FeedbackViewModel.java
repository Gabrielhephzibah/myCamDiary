package com.enyata.camdiary.ui.deliveries.deliveries_delivery.feedback;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class FeedbackViewModel extends BaseViewModel<FeedbackNavigator> {
    public FeedbackViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void  onBottle(){
        getNavigator().bottles();
    }

    public  void  onFinish(){
        getNavigator().finish();
    }
}
