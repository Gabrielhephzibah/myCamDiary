package com.enyata.camdiary.ui.aggregations.details;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class CollectorDetailViewModel extends BaseViewModel<CollectorDetailNavigator> {
    public CollectorDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onProceed(){
        getNavigator().proceed();
    }
}
