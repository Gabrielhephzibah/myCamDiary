package com.enyata.camdiary.ui.offlinecollection.offlineDashBoard;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class OfflineDashboardViewModel extends BaseViewModel<OfflineDashboardNavigator> {
    public OfflineDashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBack(){
        getNavigator().onBack();
    }
}
