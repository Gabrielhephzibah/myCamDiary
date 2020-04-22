package com.enyata.camdiary.ui.collections.data.bdsData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.BdsDataRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class BdsViewModel extends BaseViewModel<BdsDataNavigator> {
    public BdsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBioData(){
        getNavigator().onBioData();
    }

    public void onLocationInfo(){
        getNavigator().onLocationInfo();
    }

    public void onIncomeSource(){
        getNavigator().onIncomeSource();
    }

    public void onCoperativeInfo(){
        getNavigator().onCoperativeInfo();
    }

    public void onFarmInfo(){
        getNavigator().onFarmInfo();
    }

    public String getCurrentUserType(){
        return  getDataManager().getCurrentUserType();
    }

    public void onBack(){
        getNavigator().onBack();
    }

    public void onSubmitBds(){
        getNavigator().onSubmitBds();
    }

    public void onUploadPicture(){
        getNavigator().onUploadPicture();
    }

    public void onSubmitBdsDataQuestion(BdsDataRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
               .submitBdsDataQuestion(request)
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

    public void getElectoralWard(String areaCouncil){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getElectoralWard(areaCouncil)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onElectoralWardResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }


    public void onDispose(){
        onCleared();
    }
}
