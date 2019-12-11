package com.enyata.camdiary.ui.collections.successfulcollection;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class SuccessfulViewModel extends BaseViewModel<SuccessfulNavigator> {
    public SuccessfulViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onHome(){
        getNavigator().home();
    }
}
