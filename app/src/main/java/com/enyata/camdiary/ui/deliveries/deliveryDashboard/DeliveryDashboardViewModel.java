package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

import android.text.format.DateFormat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.response.Order;
import com.enyata.camdiary.data.model.api.response.Product;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DeliveryDashboardViewModel extends BaseViewModel<DeliveryDashboardNavigator> {
    public DeliveryDashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onDelivery(){
        getNavigator().delivery();
    }

    public  void  onHistory(){
        getNavigator().history();
    }



    public void  onLogOut(){
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().logout();

    }
    public  void  onSignup(){
        getNavigator().signup();
    }

    public  String getCurrentFirstName(){
       return getDataManager().getCurrentUserName();
    }

    public  String getCurrentDate(){
        return (String) DateFormat.format("dd/MM/yyyy",new java.util.Date());
    }

    public void setCustomerName(String name){
        getDataManager().setCustomerName(name);
    }

    public void getPendingDelivery(){
        getCompositeDisposable().add(getDataManager()
                .getPendingDelivery()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().getPendingDelivery(response);

                }, throwable -> {
                    getNavigator().handleError(throwable);

                }));

    }


    public  void  getDeliveriesCompleted(){
        getCompositeDisposable().add(getDataManager()
                .getDeliveryCompleted()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().getDeliveryCompleted(response);
                }, throwable -> {
                    getNavigator().handleError(throwable);

                }));

    }


    public  void  getBottleInventory(){
        getCompositeDisposable().add(getDataManager()
                .getBottleInventory()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().getBottleInventory(response);
                }, throwable -> {
                    getNavigator().handleError(throwable);

                }));
    }

    public  void  dispose(){
        onCleared();
    }

    public  void  setOrderId(String orderId){
        getDataManager().setOrderId(orderId);
    }



    /**
     * Live Data Instance
     */
    private MutableLiveData<String>deliveryCompleted  = new MutableLiveData<>();
    private MutableLiveData<String> inventoryCollected = new MutableLiveData<>();


    public void  setdeliveryCompleted (String data) {
        deliveryCompleted.setValue(data);
    }

    public LiveData<String> getDeliveryCompleted() {
        return deliveryCompleted;
    }

    public void setInventoryCollected(String data) {
        inventoryCollected.setValue(data);
    }

    public LiveData<String> getInventoryCompleted() {
        return inventoryCollected;
    }

    public  String getDispatcherImage(){
        return getDataManager().getUserImageUrl();
    }





}
