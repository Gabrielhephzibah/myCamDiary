package com.enyata.camdiary.ui.aggregations.collection.success;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class CollectionSuccessViewModel extends BaseViewModel<CollectionSuccessNavigator> {
    public CollectionSuccessViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNext(){
        getNavigator().next();
    }
}
