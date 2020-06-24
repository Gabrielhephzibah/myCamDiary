package com.enyata.camdiary.ui.aggregations.collectorCollection;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class CollectorCollectionViewModel extends BaseViewModel<CollectorCollectionNavigator> {
    public CollectorCollectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onViewMore(){
        getNavigator().onViewMore();
    }

    public void onCollection(){
        getNavigator().onCollection();
    }
}
