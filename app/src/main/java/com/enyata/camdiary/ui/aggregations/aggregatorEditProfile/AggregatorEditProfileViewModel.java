package com.enyata.camdiary.ui.aggregations.aggregatorEditProfile;

import android.text.TextUtils;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.ChangePasswordRequest;
import com.enyata.camdiary.data.model.api.request.EditProfileRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class AggregatorEditProfileViewModel extends BaseViewModel<AggregatorEditProfileNavigator> {
    public AggregatorEditProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBack(){
        getNavigator().onBack();
    }


    public void userChangePassword(ChangePasswordRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .userChangePassword(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response ->{
                    setIsLoading(false);
                    getNavigator().onResponse(response);
                },throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));

    }


    public void userEditProfile(EditProfileRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .userEditProfile(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response ->{
                    setIsLoading(false);
                    getNavigator().onEditProfile(response);
                },throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }

    public boolean isOldPasswordEmpty(String oldPassword) {
        return !TextUtils.isEmpty(oldPassword);
    }

    public boolean isLengthEqualsTothree(String newPassword) {
        return !TextUtils.isEmpty(newPassword) && newPassword.length() >= 7;
    }

    public void onDispose(){
        onCleared();
    }

    public  String getCollectorImage(){
        return getDataManager().getUserImageUrl();
    }

    public  String getUserFirstName(){
        return  getDataManager().getCurrentUserName();
    }

    public  String getUserLastName(){
        return  getDataManager().getCurrentUserLatName();
    }

    public  String getUserEmail(){
        return  getDataManager().getCurrentUserEmail();
    }

    public String getUserTpe(){
        return  getDataManager().getCurrentUserType();
    }

    public  String getUserPhoneNuber(){return  getDataManager().getCurrentUserPhoneNumber(); }

    public String getUserAddress(){return  getDataManager().getCurrentUserAddress();}



}
