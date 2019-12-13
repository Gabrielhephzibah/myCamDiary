package com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class FinishViewModel extends BaseViewModel<FinishNavigator> {
    public FinishViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onFinish(){
        getNavigator().finish();
    }
}
