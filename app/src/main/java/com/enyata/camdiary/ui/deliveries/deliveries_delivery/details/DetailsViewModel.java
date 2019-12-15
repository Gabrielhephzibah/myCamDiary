package com.enyata.camdiary.ui.deliveries.deliveries_delivery.details;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DetailsViewModel extends BaseViewModel<DetailsNavigator> {
    public DetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onDeliver(){
        getNavigator().deliver();
    }

    public  void  onBack(){
        getNavigator().back();
    }
}
