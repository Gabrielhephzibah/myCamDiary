package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DeliveryDashboardViewModel extends BaseViewModel<DeliveryDashboardNavigator> {
    public DeliveryDashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onDelivery(){
        getNavigator().delivery();
    }

    public  void  onHistory(){
        getNavigator().history();
    }

    public  void  onSignup(){
        getNavigator().signup();
    }

}
