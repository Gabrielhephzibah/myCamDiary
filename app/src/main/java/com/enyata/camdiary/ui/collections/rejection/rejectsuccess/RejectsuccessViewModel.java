package com.enyata.camdiary.ui.collections.rejection.rejectsuccess;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class RejectsuccessViewModel extends BaseViewModel<RejectsuccessNavigator> {
    public RejectsuccessViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onHome(){
        getNavigator().home();
    }
}
