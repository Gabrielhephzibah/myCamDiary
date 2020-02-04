package com.enyata.camdiary.ui.deliveries.history;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DeliveryHistoryViewModel extends BaseViewModel<DeliveryHistoryNavigator> {
    public DeliveryHistoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onDelivery(){
        getNavigator().delivery();
    }

    public  void onSignup(){
        getNavigator().signup();
    }

    public void  onLogOut(){
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().logout();
    }

    public void onBack(){
        getNavigator().back();
    }

    public void getDeliveryHistory(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getDeliveryHistory()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().deliveryHistory(response);

                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }

    public void onDispose(){
        onCleared();
    }

    public  String  getCurrentUser(){
       return getDataManager().getCurrentUserName();
    }
}
