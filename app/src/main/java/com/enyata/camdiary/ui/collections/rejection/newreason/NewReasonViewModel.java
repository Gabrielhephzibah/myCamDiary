package com.enyata.camdiary.ui.collections.rejection.newreason;

import android.util.Log;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.api.request.NewCreateCollectionRequest;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.data.remote.ApiUtils;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class NewReasonViewModel extends BaseViewModel<NewReasonNavigator> {
    public NewReasonViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    CompositeDisposable disposable = new CompositeDisposable();
    private APIService mAPIService;

    public void onSubmit(){
        getNavigator().submit();
    }

    public  void onBack(){
        getNavigator().back();
    }

    public void  deleteChurnDetails(List<ChurnDetailsData> churnDetails) {
        getDataManager().deleteChurnDetails(churnDetails);
    }
    public boolean checkIfChurnDetailsIsEmpty() {
        return getChurnDetails() == null;
    }

    public String getFarmerFullName(){ return getDataManager().getFramerName();}

    public List<ChurnDetailsData> getChurnDetails(){
        return getDataManager().getChurnDetails();
    }

    public  String getRejectedVolume(){
        return  getDataManager().getRejectedVolumee();
    }


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

    public  String getFarmerVerificationNo(){return  getDataManager().getFarmerVerificationId();}


}
