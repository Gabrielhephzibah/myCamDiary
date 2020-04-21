package com.enyata.camdiary.ui.collections.data.dataScanBarcode;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DataScanCodeViewModel extends BaseViewModel<DataScanCodeNavigator> {
    public DataScanCodeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onFarmerId(){
        getNavigator().onFarmerId();
    }

    public void onBack(){
        getNavigator().onBack();
    }


    public String getCurrentUserType(){
        return  getDataManager().getCurrentUserType();
    }

    public void  onScanBarCode(){
        getNavigator().onScanBarCode();
    }
}
