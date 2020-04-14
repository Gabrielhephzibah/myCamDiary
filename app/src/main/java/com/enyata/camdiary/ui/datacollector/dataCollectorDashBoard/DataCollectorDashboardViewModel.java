package com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import retrofit2.http.PUT;

public class DataCollectorDashboardViewModel extends BaseViewModel<DataCollectorDashBoardNavigator> {
    public DataCollectorDashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onCds() {
        getNavigator().onCds();
    }

    public void onBds() {
        getNavigator().onBds();
    }

    public void onPds() {
        getNavigator().onPds();
    }

    public void onLogOut() {
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().onLogout();
    }

    public String getCurrentUserImage(){
        return getDataManager().getUserImageUrl();
    }

    public String getCurrentUserName(){
        return  getDataManager().getCurrentUserName();
    }
}
