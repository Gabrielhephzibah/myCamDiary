package com.enyata.camdiary.ui.collections.rejection.rejectsuccess;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import java.util.List;

public class RejectsuccessViewModel extends BaseViewModel<RejectsuccessNavigator> {
    public RejectsuccessViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onHome(){
        getNavigator().home();
    }

    public String getFarmerFullName(){ return getDataManager().getFramerName();}

    public  String getRejectedVolume(){
        return  getDataManager().getRejectedVolumee();
    }

    public void  deleteChurnDetails(List<ChurnDetailsData> churnDetails) {
        getDataManager().deleteChurnDetails(churnDetails);
    }
    public List<ChurnDetailsData> getChurnDetails(){
        return getDataManager().getChurnDetails();
    }
}
