package com.enyata.camdiary.ui.editProfile;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class EditProfileViewModel extends BaseViewModel<EditProfileNavigator> {
    public EditProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBack(){
        getNavigator().onBack();
    }
}
