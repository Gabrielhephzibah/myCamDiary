package com.enyata.camdiary.ui.deliveries.signcustomer.confirmsuccess;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class SignsuccessViewModel extends BaseViewModel<SignsuccessNavigator> {
    public SignsuccessViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void  onHome(){
        getNavigator().home();
    }
}
