package com.enyata.camdiary.ui.scanbarcode.aggregatorScanBarCode;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class AggregatorBarcodeViewModel extends BaseViewModel<AggregatorBarcodeNavigator> {
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

    public void scanCollectorBarcode(String verification_id) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getCollectorDetails(verification_id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onResponse(response);
                    getDataManager().setCollectorId(String.valueOf(response.getData().getId()));
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }


    public void setCollectorName(String name){
        getDataManager().setCollectorName(name);
    }
}
