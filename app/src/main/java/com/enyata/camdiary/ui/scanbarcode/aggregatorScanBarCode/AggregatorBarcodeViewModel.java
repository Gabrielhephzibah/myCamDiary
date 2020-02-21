package com.enyata.camdiary.ui.scanbarcode.aggregatorScanBarCode;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class AggregatorBarcodeViewModel extends BaseViewModel {
    public AggregatorBarcodeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public String getAccessToken(){
        return  getDataManager().getAccessToken();
    }

    public  void setCollectorVerificationId(String verificationId){
        getDataManager().setCollectorVerificationId(verificationId);
    }

    public  String getCollectorVerificationId(){
        return  getDataManager().getCollectorVerificationId();
    }

   public  void setCollectorId(String id){
        getDataManager().setCollectorId(id);
   }

    public void onDispose(){
        onCleared();
    }
}
