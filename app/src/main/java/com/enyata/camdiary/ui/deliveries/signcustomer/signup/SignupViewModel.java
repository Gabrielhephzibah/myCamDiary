package com.enyata.camdiary.ui.deliveries.signcustomer.signup;

import android.text.TextUtils;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.DispatcherSignUpRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.CommonUtils;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class SignupViewModel extends BaseViewModel<SignupNavigator> {
    public SignupViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onSubmit() {
        getNavigator().submit();
    }

    public void onLogout() {
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().logout();
    }



    public boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && CommonUtils.isEmailValid(email);
    }

    public boolean isPhoneNumberValid(String phoneNumber){
        return !TextUtils.isEmpty(phoneNumber) & phoneNumber.length() >= 11;
    }

    public boolean isLengthEqualsTothree(String firstName) {
        return !TextUtils.isEmpty(firstName) && firstName.length() >= 3;
    }

    public boolean isLengthuptoTothree(String lastName) {
        return !TextUtils.isEmpty(lastName) && lastName.length() >= 3;
    }

    public  boolean isAddressEmpty(String address){
        return !TextUtils.isEmpty(address);
    }
    public void onBack() {
        getNavigator().onBack();
    }

    public  void dispatcherSignup(DispatcherSignUpRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .dispatcherSignUp(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().dispatcherSignup(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }
}
