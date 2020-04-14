package com.enyata.camdiary.ui.collections.farmer.farmerDetails;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import retrofit2.http.PUT;

public class FarmerDetailsViewModel extends BaseViewModel<FarmerDetailsNavigator> {
    public FarmerDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onProceed(){
        getNavigator().proceed();

    }

    public void onGoScan(){
        getNavigator().goscan();
    }

    public void dispose(){
        onCleared();
    }

    public String getFarmerFullName(){ return getDataManager().getFramerName();}

    public String  getFramerPhoneNo(){return  getDataManager().getFarmerPhoneNumber();}

    public  String getFarmerCoperative(){return  getDataManager().getFarmerCooperativeName();}

    public  String getFarmerVerificationNo(){return  getDataManager().getFarmerVerificationId();}

}
