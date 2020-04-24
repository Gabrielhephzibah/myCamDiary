package com.enyata.camdiary.ui.deliveries.deliveries_delivery.details;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DetailsViewModel extends BaseViewModel<DetailsNavigator> {
    public DetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onDeliver() {
        getNavigator().deliver();
    }

    public void onBack() {
        getNavigator().back();
    }

    public String getShopifyId() {
        return getDataManager().getShopifyId();
    }



    public  void  getOrderDetails(String shopifyId){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getOrderDetails(shopifyId)
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


    public void  onDispose(){
        onCleared();
    }

    public  void  setOrderId(String orderId){
        getDataManager().setOrderId(orderId);
    }

    public void setCustomerName(String name){
        getDataManager().setCustomerName(name);
    }


}
