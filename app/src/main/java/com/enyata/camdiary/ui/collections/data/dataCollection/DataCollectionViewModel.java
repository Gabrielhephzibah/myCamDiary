package com.enyata.camdiary.ui.collections.data.dataCollection;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DataCollectionViewModel extends BaseViewModel<DataCollectionNavigator> {
    public DataCollectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void onSubmit(){
        getNavigator().submit();
    }


}
