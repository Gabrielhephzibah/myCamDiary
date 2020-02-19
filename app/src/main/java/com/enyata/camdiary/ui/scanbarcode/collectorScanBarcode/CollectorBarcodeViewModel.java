package com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class CollectorBarcodeViewModel extends BaseViewModel {
    public CollectorBarcodeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

public  void  setfarmerVerificationId(String verificationId){
        getDataManager().setFarmerVerificationId(verificationId);
}

public  String getFarmerVerificationId(){
      return   getDataManager().getFarmerVerificationId();
}

    public String getAccessToken(){
        return getDataManager().getAccessToken();
    }

    public  void  setFarmerId( String id){
        getDataManager().setFarmerId(id);
    }


}
