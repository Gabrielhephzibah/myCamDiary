package com.enyata.camdiary.ui.offlinecollection.offlineDashBoard;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.local.db.DataBaseClient;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;

public class OfflineDashboardViewModel extends BaseViewModel<OfflineDashboardNavigator> {
    public OfflineDashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBackToLogin(){
        getNavigator().onBackToLogin();

    }

    public boolean checkIfChurnDetailsIsEmpty() {
        return getChurnDetails() == null;
    }

    public void setChurnDetails(List<ChurnDetailsData> churnDetails) {
        getDataManager().setChurnDetails(churnDetails);
    }

    public void  deleteChurnDetails(List<ChurnDetailsData> churnDetails) {
        getDataManager().deleteChurnDetails(churnDetails);
    }



    public List<ChurnDetailsData> getChurnDetails(){
        return getDataManager().getChurnDetails();
    }


    public void saveChurnDetailsToLocalStorage(ChurnDetailsData churnDetailsData) {
        if (checkIfChurnDetailsIsEmpty()) {
            List<ChurnDetailsData> newArray = new ArrayList<>();
            newArray.add(churnDetailsData);
            setChurnDetails(newArray);

        } else {
            List<ChurnDetailsData> oldArray = getChurnDetails();
            Log.i("jjjjjj", "Already");
            for (int i = 0; i < oldArray.size(); i++) {
                if (oldArray.get(i).getChurnId().equals(churnDetailsData.getChurnId()) && oldArray.get(i).equals(churnDetailsData.getVolume())) {
                    setChurnDetails(oldArray);
                    Log.i("NEW NEW", String.valueOf(getChurnDetails()));
                    break;
                } else {
                    if (getChurnDetails().contains(churnDetailsData)) {
                    }
                    List<ChurnDetailsData> sameArray = getChurnDetails();
                    setChurnDetails(sameArray);


                }
            }

        }
        if (!getChurnDetails().contains(churnDetailsData)) {
            List<ChurnDetailsData> addArray = getChurnDetails();
            addArray.add(churnDetailsData);
            setChurnDetails(addArray);

        }


        Log.i("ChurnDetailArray", String.valueOf(getChurnDetails()));

    }

    public void addNewMilkCollection(MilkCollection milkCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addNewMilkCollectionData(milkCollection)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(() -> {
                    setIsLoading(false);
                    getNavigator().onResponse();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void setOfflineFarmerId(String offlineFarmerId){
        getDataManager().setOfflineFarmerId(offlineFarmerId);

    }

    public  boolean isChurnIdInArray(){
        if (!checkIfChurnDetailsIsEmpty()){
            List<ChurnDetailsData>churnDetails = getChurnDetails();
            for(int i = 0; i<churnDetails.size(); i++){
                if (!churnDetails.get(i).getChurnId().equals(0)){
                    return true;
                }
            }

        }
        return  false;
    }


}
