package com.enyata.camdiary.ui.collections.farmer.farmerId;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class FarmerIdViewModel extends BaseViewModel<FarmerIdNavigator> {
    public FarmerIdViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAccept(){
        getNavigator().accept();
    }

    public void onBack(){
        getNavigator().back();
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





    public void dispose(){
        onCleared();
    }

    public  void  setFarmerId(String id){
        getDataManager().setFarmerId(id);

    }

    public void setFarmerFullName(String name){getDataManager().setFarmerName(name);}

    public  void  setFarmerPhoneNo(String  farmerPhoneNo){getDataManager().setFarmerPhoneNumber(farmerPhoneNo);}

    public void  setFarmerCoperative(String coperativeName){getDataManager().setFarmerCooperativeName(coperativeName);}

    public void setFarmerVerificationId(String verificationId){getDataManager().setFarmerVerificationId(verificationId);}
}
