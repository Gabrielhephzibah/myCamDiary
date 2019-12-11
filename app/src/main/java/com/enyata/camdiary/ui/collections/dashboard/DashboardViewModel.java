package com.enyata.camdiary.ui.collections.dashboard;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DashboardViewModel extends BaseViewModel<DashboardNavigator> {
    public DashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void  onScancode(){
        getNavigator().scancode();
    }

    public void onHistory(){
        getNavigator().history();
    }

    public void onDataCollection(){
        getNavigator().dataCollection();
    }
}
