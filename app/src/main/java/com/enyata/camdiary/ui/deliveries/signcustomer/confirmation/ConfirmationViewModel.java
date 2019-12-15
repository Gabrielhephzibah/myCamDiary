package com.enyata.camdiary.ui.deliveries.signcustomer.confirmation;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class ConfirmationViewModel extends BaseViewModel<ConfirmationNavigator> {
    public ConfirmationViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void  onBack(){
        getNavigator().back();
    }

    public void onConfirm(){
        getNavigator().confirm();
    }
}
