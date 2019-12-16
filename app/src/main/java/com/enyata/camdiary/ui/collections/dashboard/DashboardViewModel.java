package com.enyata.camdiary.ui.collections.dashboard;

import android.text.format.DateFormat;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.CamLoginRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DashboardViewModel extends BaseViewModel<DashboardNavigator> {
    public DashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void  onScancode(){
        getNavigator().scancode();
    }

    public void onHistory(){
        getNavigator().history();
    }

    public void onDataCollection(){
        getNavigator().dataCollection();
    }

    public void onLogout(){
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().logout();
    }

    public void getVolumeOfAcceptedCollection() {
        getCompositeDisposable().add(getDataManager()
                .getAcceptedVolume()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().displayAcceptedVolume(response);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }

    public void getVolumeOfRejectedCollection() {
        getCompositeDisposable().add(getDataManager()
                .getRejectedVolume()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().displayRejectedVolume(response);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }

    public void getAllEntries(){
        getCompositeDisposable().add(getDataManager()
                .getAllEntries()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().getAllEntries(response);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }

    public void getTodaysCollection(){
        getCompositeDisposable().add(getDataManager()
                .getTodaysCollection()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().getTodayCollection(response);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }

    public void dispose(){
        onCleared();
    }

    public String getCurrentDate(){
        return (String) DateFormat.format("dd/MM/yyyy", new java.util.Date());
    }

    /**
     * Live Data Instance
     */
    private MutableLiveData<String> acceptedVolume = new MutableLiveData<>();
    private MutableLiveData<String> rejectedVolume = new MutableLiveData<>();
    private MutableLiveData<String> entries  = new MutableLiveData<>();

    public void setAcceptedVolume(String volume) {
        acceptedVolume.setValue(volume);
    }

    public LiveData<String> getAcceptedVolume() {
        return acceptedVolume;
    }

    public void setRejectedVolume(String volume) {
        rejectedVolume.setValue(volume);
    }

    public LiveData<String> getRejectedVolume() {
        return rejectedVolume;
    }

    public void setEntries(String entry) {
        entries.setValue(entry);
    }

    public LiveData<String> getEntries() {
        return entries;
    }

    public String getFirstName(){
        return getDataManager().getCurrentUserName();
    }

}
