package com.enyata.camdiary.ui.collections.newentervolume;

import android.util.Log;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.api.request.NewCreateCollectionRequest;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.data.remote.ApiUtils;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewEnterVolumeViewModel extends BaseViewModel<NewEnterVolumeNavigator> {
    public NewEnterVolumeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    CompositeDisposable disposable = new CompositeDisposable();
    private APIService mAPIService;

    public void onAccept() {
        getNavigator().accept();
    }


    public void onReject() {
        getNavigator().reject();
    }

    public void onBack() {
        getNavigator().back();
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



    public String getFarmerFullName(){ return getDataManager().getFramerName();}


    public void createCollection(NewCreateCollectionRequest request) {
        getNavigator().onStarting();
        mAPIService = ApiUtils.getAPIService();
        disposable.add(
                mAPIService.createNewCollection(getDataManager().getAccessToken(),request )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            getNavigator().onResponse(response);
//                            Intent i = new Intent(getApplicationContext(), ResponseActivity.class);
//                            startActivity(i);
                            Log.i("RESPONSE","RESPONSE IS SUCESSFULK");
                        },throwable -> {
                            Log.i("Error","ERRROR");
                            getNavigator().handleError(throwable);

//
                        }));
    }


    public String getFarmerId(){
        return getDataManager().getFarmerId();
    }

    public  String getFarmerVerificationNo(){return  getDataManager().getFarmerVerificationId();}

    public void dispose(){
        onCleared();
    }

    public void  onDisposableDispose(){
        disposable.dispose();
    }

    public void setRejectedVolume(String rejectedVolume){
        getDataManager().setRejectionVolumee(rejectedVolume);
    }
}
