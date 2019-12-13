package com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DeliveryViewModel extends BaseViewModel<DeliveryNavigator> {
    public DeliveryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void onClick(){
        getNavigator().click();
    }

    public  void  onBack(){
        getNavigator().back();
    }
}
