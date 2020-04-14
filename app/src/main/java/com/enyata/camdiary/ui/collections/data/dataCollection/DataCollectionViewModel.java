package com.enyata.camdiary.ui.collections.data.dataCollection;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class DataCollectionViewModel extends BaseViewModel<DataCollectionNavigator> {
    public DataCollectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void onSubmit(){
        getNavigator().submit();
    }

    public  void onBack(){
        getNavigator().back();
    }

    public  void onBDS(){
        getNavigator().onBDS();
    }

    public void onCDS(){
        getNavigator().onCDS();
    }

    public void onPDS(){
        getNavigator().onPDS();
    }

    public void onScanBarcode(){
        getNavigator().onScanbarCode();
    }

    public void onHistory(){
        getNavigator().onHistory();
    }

    public void onLogout(){
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().onLogOut();

    }

    public String getUserImage(){
        return  getDataManager().getUserImageUrl();
    }


}
