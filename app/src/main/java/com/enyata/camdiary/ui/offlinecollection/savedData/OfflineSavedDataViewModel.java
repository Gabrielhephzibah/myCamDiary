package com.enyata.camdiary.ui.offlinecollection.savedData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class OfflineSavedDataViewModel extends BaseViewModel<OfflineSavedDataNavigator> {
    public OfflineSavedDataViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onBack(){
        getNavigator().onBack();
    }
}
