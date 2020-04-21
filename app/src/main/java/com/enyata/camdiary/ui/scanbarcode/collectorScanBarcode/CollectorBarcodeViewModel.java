package com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class CollectorBarcodeViewModel extends BaseViewModel<CollectorBarcodeNavigator>{
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


    public void  onDispose(){
        onCleared();

    }


   public  void scanCollectorBarCode(String verificationId){
        setIsLoading(true);
       getCompositeDisposable().add(getDataManager()
               .getFarmerInfo(verificationId)
               .subscribeOn(getSchedulerProvider().io())
               .observeOn(getSchedulerProvider().ui())
               .subscribe(response -> {
                   setIsLoading(false);
                   getNavigator().onResponse(response);
               }, throwable -> {
                   setIsLoading(false);
                   getNavigator().handleError(throwable);
               }));
   }

    public void setFarmerFullName(String name){getDataManager().setFarmerName(name);}

    public  void  setFarmerPhoneNo(String  farmerPhoneNo){getDataManager().setFarmerPhoneNumber(farmerPhoneNo);}

    public void  setFarmerCoperative(String coperativeName){getDataManager().setFarmerCooperativeName(coperativeName);}

    public void setFarmerVerificationId(String verificationId){getDataManager().setFarmerVerificationId(verificationId);}

}
