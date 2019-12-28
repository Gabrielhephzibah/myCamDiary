package com.enyata.camdiary.ui.collections.data.dataSubmission;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class SubmissionViewModel extends BaseViewModel<SubmissionNavigator> {
    public SubmissionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onHistory(){
        getNavigator().history();
    }

    public void ondataCollection(){
        getNavigator().history();
    }

    public  void onBarcode(){
        getNavigator().scan();
    }

    public  void onHome(){
        getNavigator().home();
    }

    public  void onLogout(){
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().logout();
    }
}
