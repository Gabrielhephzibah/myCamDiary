package com.enyata.camdiary.ui.aggregations.details;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class CollectorDetailViewModel extends BaseViewModel<CollectorDetailNavigator> {
    public CollectorDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onProceed(){
        getNavigator().proceed();
    }

    public  void onBack(){
        getNavigator().back();
    }

    public String getCollectorId(){
        return getDataManager().getCollectorId();
    }

    public  String getCollectorFullName(){
        return  getDataManager().getCollectorName();
    }

    public String getCollectorPhoneNumber(){
        return  getDataManager().getCollectorPhoneNo();
    }

    public String getCollectorEmail(){
        return getDataManager().getCollectorEmail();
    }

    public  String getCollectorVerificationId(){
        return getDataManager().getCollectorVerificationId();
    }


}
