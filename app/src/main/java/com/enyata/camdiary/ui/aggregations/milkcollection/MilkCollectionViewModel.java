package com.enyata.camdiary.ui.aggregations.milkcollection;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class MilkCollectionViewModel extends BaseViewModel<MilkCollectionNavigator> {
    public MilkCollectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }



    public void onBack(){
        getNavigator().onBack();
    }

    public void onProceed(){
        getNavigator().onProceed();
    }
}
