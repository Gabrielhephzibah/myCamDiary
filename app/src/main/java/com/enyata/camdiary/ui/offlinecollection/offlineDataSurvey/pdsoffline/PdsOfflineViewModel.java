package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.pdsoffline;

import com.enyata.camdiary.data.DataManager;
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
}
