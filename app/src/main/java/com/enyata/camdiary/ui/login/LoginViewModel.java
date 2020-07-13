

package com.enyata.camdiary.ui.login;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.CommonUtils;
import com.enyata.camdiary.utils.rx.SchedulerProvider;
import com.google.gson.JsonSyntaxException;

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
                    try {
                        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_IN);
                    getNavigator().onResponse(response);
                    String token = response.getData().getToken();
                    String userEmail = response.getData().getEmail();
                    String firstname = response.getData().getFirstName();
                    String image_url = response.getData().getImageUrl();
                    String usertype = response.getData().getUserType();
                    String lastname = response.getData().getLastName();
                    String address = response.getData().getContactAddress();
                    String phoneNo = response.getData().getContactNo();
                    getDataManager().updateUserInfo(token,firstname,lastname,usertype,userEmail,image_url,phoneNo,address);
                    if(response.getData().getUserType().equals("collector")){
                        getNavigator().goToDashBoard("collector");
                        getDataManager().setLoggedInView("collector");
                    }else if(response.getData().getUserType().equals("aggregator")){
                        getNavigator().goToDashBoard("aggregator");
                        getDataManager().setLoggedInView("aggregator");
                    }else if (response.getData().getUserType().equals("dispatcher")){
                        getNavigator().goToDashBoard("dispatcher");
                        getDataManager().setLoggedInView("dispatcher");
                    }else if (response.getData().getUserType().equals("data collector")){
                        getNavigator().goToDashBoard("data collector");
                        getDataManager().setLoggedInView("data collector");
                    }
                    }catch (NullPointerException | IllegalStateException | ClassCastException | JsonSyntaxException exception){
                        Log.d("USERTYPE", "uSER_TYPE DOES NOT EXIST");
                        getNavigator().onCatchError();
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

    public void onGoOffline(){
        getNavigator().onGoOffline();
    }

    public  String getUserType(){
        return  getDataManager().getUserType();
    }

//    getDataManager().updateUserInfo(token,firstname,userEmail,image_url);

}

