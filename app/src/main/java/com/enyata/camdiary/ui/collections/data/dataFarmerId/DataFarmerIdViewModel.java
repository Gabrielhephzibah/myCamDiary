package com.enyata.camdiary.ui.collections.data.dataFarmerId;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DataFarmerIdViewModel extends BaseViewModel<DataFarmerIdNavigator> {
    public DataFarmerIdViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAccept(){
        getNavigator().onAccept();
    }

    public void onBack(){
        getNavigator().onBack();
    }

    public void getFarmerDetails(String verificationId){
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
