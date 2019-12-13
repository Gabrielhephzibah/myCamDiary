package com.enyata.camdiary.ui.aggregations.barcode.collectorID;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class ColectorIdViewModel extends BaseViewModel<CollectorIdNavigator> {

    public ColectorIdViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void onAccept(){
        getNavigator().accept();
    }

    public  void  onBack(){
        getNavigator().back();
    }
}
