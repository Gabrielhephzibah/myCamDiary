package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.pdsoffline;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class PdsOfflineViewModel extends BaseViewModel<PdsOfflineNavigator> {
    public PdsOfflineViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
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

    public void onBioData(){
        getNavigator().onBioData();
    }

    public void addNewPdsData(PdsDataCollection pdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addNewPdsData(pdsDataCollection)
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







    public void onDispose(){
        onCleared();
    }
}
