package com.enyata.camdiary.ui.collections.farmer.farmerId;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.response.FarmerDetails;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class FarmerIdViewModel extends BaseViewModel<FarmerIdNavigator> {
    public FarmerIdViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAccept(){
        getNavigator().accept();
    }


    public void getFarmerDetails(String id){
        getCompositeDisposable().add(getDataManager()
                .getFarmerDetails(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().onResponse(response);
                    getDataManager().setFarmerId(String.valueOf(response.getData()));
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));

    }


    public void dispose(){
        onCleared();
    }
}
