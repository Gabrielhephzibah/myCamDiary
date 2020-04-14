package com.enyata.camdiary.ui.collections.data.dataFarmerDetail;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DataFarmerDetailViewModel extends BaseViewModel<DataFarmerDetailNavigator> {
    public DataFarmerDetailViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBack(){
        getNavigator().onBack();
    }

    public void onProceed(){
    getNavigator().onProceed();}

    public String getFarmerFullName(){ return getDataManager().getFramerName();}

    public String  getFramerPhoneNo(){return  getDataManager().getFarmerPhoneNumber();}

    public  String getFarmerCoperative(){return  getDataManager().getFarmerCooperativeName();}

    public  String getFarmerVerificationNo(){return  getDataManager().getFarmerVerificationId();}
}
