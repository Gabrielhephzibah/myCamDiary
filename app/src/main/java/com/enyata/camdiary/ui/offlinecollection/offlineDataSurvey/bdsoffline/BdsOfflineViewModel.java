package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.bdsoffline;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class BdsOfflineViewModel extends BaseViewModel<BdsOfflineNavigator> {
    public BdsOfflineViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
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

    public void onBack(){
        getNavigator().onBack();
    }

    public void onUploadPicture(){
        getNavigator().onUploadPicture();
    }

    public void onSubmitBds(){
        getNavigator().onSubmitBds();
    }
}
