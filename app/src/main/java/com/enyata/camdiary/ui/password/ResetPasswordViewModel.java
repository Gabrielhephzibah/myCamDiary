package com.enyata.camdiary.ui.password;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.ResetPasswordRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class ResetPasswordViewModel extends BaseViewModel<ResetPasswordNavigator> {
    public ResetPasswordViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBack(){
        getNavigator().onBack();
    }

    public void onSubmit(){
        getNavigator().onSubmit();
    }

    public void  resetPassword(ResetPasswordRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .resetPassword(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onResetResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

}
