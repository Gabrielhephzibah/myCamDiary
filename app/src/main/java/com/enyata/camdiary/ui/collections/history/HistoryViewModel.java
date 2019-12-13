package com.enyata.camdiary.ui.collections.history;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class HistoryViewModel extends BaseViewModel<HistoryNavigator> {

    public HistoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void  onScancode(){
        getNavigator().scan();
    }

    public void onDataCollection(){
        getNavigator().dataCollection();
    }

    public void onBack(){
        getNavigator().back();
    }

}
