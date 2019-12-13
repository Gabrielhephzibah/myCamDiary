package com.enyata.camdiary.ui.deliveries.history;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DeliveryHistoryViewModel extends BaseViewModel<DeliveryHistoryNavigator> {
    public DeliveryHistoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onDelivery(){
        getNavigator().delivery();
    }

    public  void onSignup(){
        getNavigator().signup();
    }
}
