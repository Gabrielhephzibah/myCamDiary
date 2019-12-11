package com.enyata.camdiary.ui.collections.entervolume;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class EnterVolumeViewModel extends BaseViewModel<EnterVolumeNavigator> {
    public EnterVolumeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onAccept(){
        getNavigator().accept();
    }

    public void onReject(){
        getNavigator().reject();
    }
}
