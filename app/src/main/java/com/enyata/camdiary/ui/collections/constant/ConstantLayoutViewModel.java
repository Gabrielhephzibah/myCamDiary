package com.enyata.camdiary.ui.collections.constant;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class ConstantLayoutViewModel extends BaseViewModel<ConstantLayoutNavigator> {
    public ConstantLayoutViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public  void onHistory(){
        getNavigator().history();
    }

    public void  onDataCollection(){
        getNavigator().dataCollection();
    }

    public void onScancode(){
        getNavigator().scan();
    }
}
