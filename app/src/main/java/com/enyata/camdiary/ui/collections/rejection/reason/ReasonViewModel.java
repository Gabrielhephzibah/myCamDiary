package com.enyata.camdiary.ui.collections.rejection.reason;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class ReasonViewModel extends BaseViewModel<ReasonNavigator> {

    public ReasonViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

   public void onSubmit(){
        getNavigator().submit();
   }
}
