package com.enyata.camdiary.ui.collections.data.pdsData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.request.PdsDataRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class PdsDataViewModel extends BaseViewModel<PdsDataNavigator> {
    public PdsDataViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onIncomeSource(){
        getNavigator().onIncomeSource();
    }

    public void onFarmInfo(){
        getNavigator().onFarmInfo();
    }

    public  void onSubmitPds(){getNavigator().onSubmitPds();}

    public void onBack(){getNavigator().onBack();}

    public  String getFarmerVerificationNo(){return  getDataManager().getFarmerVerificationId();}

    public String getCurrentUserType(){
        return getDataManager().getCurrentUserType();
    }

    public void submitPdsDataCollection(PdsDataRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
               .submitPdsDataQuestion(request)
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
