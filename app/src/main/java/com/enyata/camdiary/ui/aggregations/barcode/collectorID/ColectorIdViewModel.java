package com.enyata.camdiary.ui.aggregations.barcode.collectorID;

import android.util.Log;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class ColectorIdViewModel extends BaseViewModel<CollectorIdNavigator> {

    public ColectorIdViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAccept() {
        getNavigator().accept();
    }

    public void onBack() {
        getNavigator().back();
    }

    public void getCollectorDetails(String verification_id) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getCollectorDetails(verification_id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().getCollectorDetails(response);
                    getDataManager().setCollectorId(String.valueOf(response.getData().getId()));
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void setCollectorName(String name){
        getDataManager().setCollectorName(name);
    }


    public void dispose() {
        onCleared();
    }

    public void setCollectorId(String id){
        getDataManager().setCollectorId(id);
    }
}
