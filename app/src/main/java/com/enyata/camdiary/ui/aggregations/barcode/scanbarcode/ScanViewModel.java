package com.enyata.camdiary.ui.aggregations.barcode.scanbarcode;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class ScanViewModel extends BaseViewModel<ScanNavigator> {
    public ScanViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onScan(){
        getNavigator().scan();
    }

    public void onEnterId(){
        getNavigator().enterId();
    }

    public  void  onBack(){
        getNavigator().back();
    }
}
