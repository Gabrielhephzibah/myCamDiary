package com.enyata.camdiary.ui.deliveries.bottles;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.DeliveryCollection;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class BottlesViewModel extends BaseViewModel<BottlesNavigator> {
    public BottlesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onFinish(){
        getNavigator().finish();
    }

    public  String getCustomerName(){
        return getDataManager().getCustomerName();
    }


    public  void  onBack(){
        getNavigator().onBack();
    }

    public  String getOrderId(){
        return getDataManager().getOrderId();
    }

    public  void addNewDelivery(DeliveryCollection.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addNewDelivery(request)
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

   public  void onDispose(){
        onCleared();
   }

   public  String getCustomerPhoneNo(){
        return  getDataManager().getCustomerPhoneNo();
   }


}
