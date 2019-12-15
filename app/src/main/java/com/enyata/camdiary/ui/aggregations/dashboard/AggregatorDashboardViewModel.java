package com.enyata.camdiary.ui.aggregations.dashboard;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class AggregatorDashboardViewModel extends BaseViewModel<AggregatorDashboardNavigator> {
    public AggregatorDashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void  onHistory(){
        getNavigator().history();
    }

    public  void  onScancode(){
        getNavigator().scan();
    }

    public void onLogout(){
        getNavigator().out();
    }


}
