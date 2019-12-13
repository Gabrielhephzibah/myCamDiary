package com.enyata.camdiary.ui.aggregations.collection.last;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class LastCollectionViewModel extends BaseViewModel<LastCollectionNavigator> {
    public LastCollectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onFinish(){
        getNavigator().finish();
    }
}
