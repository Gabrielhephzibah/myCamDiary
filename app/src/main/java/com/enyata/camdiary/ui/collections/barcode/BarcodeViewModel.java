package com.enyata.camdiary.ui.collections.barcode;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class BarcodeViewModel extends BaseViewModel<BarcodeNavigator> {
    public BarcodeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onScanCode() {
        getNavigator().scan();
    }

    public  void onEnterFarmerId(){
        getNavigator().enterid();
    }

    public void onGoback(){
        getNavigator().back();
    }

}


