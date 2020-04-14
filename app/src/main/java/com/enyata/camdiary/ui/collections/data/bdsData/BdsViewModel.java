package com.enyata.camdiary.ui.collections.data.bdsData;

import com.enyata.camdiary.data.DataManager;
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
}
