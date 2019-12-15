package com.enyata.camdiary.ui.deliveries.bottles;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class BottlesViewModel extends BaseViewModel<BottlesNavigator> {
    public BottlesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onFinish(){
        getNavigator().finish();
    }
}
