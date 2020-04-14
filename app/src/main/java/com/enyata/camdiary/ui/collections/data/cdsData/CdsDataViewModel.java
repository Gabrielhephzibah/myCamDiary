package com.enyata.camdiary.ui.collections.data.cdsData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import retrofit2.http.PUT;

public class CdsDataViewModel extends BaseViewModel<CdsNavigator> {
    public CdsDataViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void onBioData(){
        getNavigator().onBioData();
    }

    public void onLocationInfo(){
        getNavigator().onLocationInfo();
    }

    public void onIncomeSources(){
        getNavigator().onIncomeSources();
    }

    public void onFarmInfo(){
        getNavigator().onFarmInfo();
    }

    public void onBack(){
        getNavigator().onBack();
    }

    public String getCurrentUserType(){
        return getDataManager().getCurrentUserType();
    }

    public void submitCdsDataCollection(CdsDataRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
               .submitCdsDataQuestion(request)
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

    public void onDispose(){
        onCleared();
    }
}
