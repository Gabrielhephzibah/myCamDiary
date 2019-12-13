package com.enyata.camdiary.ui.aggregations.entervolume;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class VolumeViewModel extends BaseViewModel<VolumeNavigator> {
    public VolumeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAccept(){
        getNavigator().accept();
    }

    public  void  onBack(){
        getNavigator().back();
    }
}
