package com.enyata.camdiary.ui.collections.history;

import android.text.format.DateFormat;
import android.util.Log;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class HistoryViewModel extends BaseViewModel<HistoryNavigator> {

    public HistoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void  onScancode(){
        getNavigator().scan();
    }

    public void onDataCollection(){
        getNavigator().dataCollection();
    }

    public void onBack(){
        getNavigator().back();
    }


    public  void  getCollectionHistory(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getCollectionHistory()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().getCollectionHistory(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void dispose(){
        onCleared();
    }

    public String getCurrentDate(){
        return (String) DateFormat.format("dd/MM/yyyy", new java.util.Date());
    }

    public String getUserType(){
        return getDataManager().getUserType();
    }

    public  void onLogout(){
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().logout();
    }

    public String getAccessToken(){
        return getDataManager().getAccessToken();
    }

    public  String getCuurentUser(){
        return getDataManager().getCurrentUserName();
    }



}
