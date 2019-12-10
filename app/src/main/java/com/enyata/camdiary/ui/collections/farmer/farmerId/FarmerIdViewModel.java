package com.enyata.camdiary.ui.collections.farmer.farmerId;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class FarmerIdViewModel extends BaseViewModel<FarmerIdNavigator> {
    public FarmerIdViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAccept(){
        getNavigator().accept();
    }
}
