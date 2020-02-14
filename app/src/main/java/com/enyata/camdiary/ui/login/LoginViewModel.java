

package com.enyata.camdiary.ui.login;

import android.text.TextUtils;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.CommonUtils;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

/**
 * Created by Sanni Michael on 10/12/2019.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        return !TextUtils.isEmpty(email) && CommonUtils.isEmailValid(email) && !TextUtils.isEmpty(password);
    }

    public boolean isLengthEqualsToSeven(String password) {
        return password.length() >= 7;
    }


    public void login(String email, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .login(new CamLogin.Request(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_IN);
                    getNavigator().onResponse(response);
                    String token = response.getData().getToken();
                    String userEmail = response.getData().getEmail();
                    String firstname = response.getData().getFirstName();
                    String image_url = response.getData().getImageUrl();
                    getDataManager().updateUserInfo(token,firstname,userEmail,image_url);
                    if(response.getData().getUserType().equals("collectors")){
                        getNavigator().goToDashBoard("collectors");
                        getDataManager().setLoggedInView("collector");
                    }else if(response.getData().getUserType().equals("aggregator")){
                        getNavigator().goToDashBoard("aggregator");
                        getDataManager().setLoggedInView("aggregator");
                    }else if (response.getData().getUserType().equals("delivery")){
                        getNavigator().goToDashBoard("delivery");
                        getDataManager().setLoggedInView("delivery");
                    }else if (response.getData().getUserType().equals("data_collectors")){
                        getNavigator().goToDashBoard("collectors");
                        getDataManager().setLoggedInView("data_collectors");
                    }

                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void onLoginClick() {
        getNavigator().loginClick();
    }

    public void onForgotPassword(){
        getNavigator().onForgotPassword();
    }

    public void setUserType(String user){
        getDataManager().setUserType(user);
    }

    public  String getUserType(){
        return  getDataManager().getUserType();
    }



}

