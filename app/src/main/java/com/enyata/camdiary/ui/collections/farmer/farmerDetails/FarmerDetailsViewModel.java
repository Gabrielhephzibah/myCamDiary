package com.enyata.camdiary.ui.collections.farmer.farmerDetails;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class FarmerDetailsViewModel extends BaseViewModel<FarmerDetailsNavigator> {
    public FarmerDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onProceed(){
        getNavigator().proceed();

    }


}
