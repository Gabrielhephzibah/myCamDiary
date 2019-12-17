package com.enyata.camdiary.ui.collections.statusofcollection;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class StatusOfCollectionViewModel extends BaseViewModel<StatusOfCollectionNavigator> {
    public StatusOfCollectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onHome(){
        getNavigator().home();
    }
}
