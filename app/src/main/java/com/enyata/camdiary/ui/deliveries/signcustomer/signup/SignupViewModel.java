package com.enyata.camdiary.ui.deliveries.signcustomer.signup;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class SignupViewModel extends BaseViewModel<SignupNavigator> {
    public SignupViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void onSubmit(){
        getNavigator().submit();
    }
}
