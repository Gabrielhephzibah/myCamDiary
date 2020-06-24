package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editCdsOffline;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class EditCdsOfflineViewModel extends BaseViewModel<EditCdsOfflineNavigator> {
    public EditCdsOfflineViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
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

    public void onSubmitCds(){
        getNavigator().onSubmitCDS();
    }

    public void updateCdsData(CdsDataCollection cdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .updateCdsData(cdsDataCollection)
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
