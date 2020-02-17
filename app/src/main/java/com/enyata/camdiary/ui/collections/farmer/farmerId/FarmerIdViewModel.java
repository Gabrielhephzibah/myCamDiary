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



    public void getFarmerDetails(String id){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getFarmerDetails(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onResponse(response);
                    getDataManager().setFarmerId(String.valueOf(response.getData().getId()));
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }




    public void dispose(){
        onCleared();
    }
}
