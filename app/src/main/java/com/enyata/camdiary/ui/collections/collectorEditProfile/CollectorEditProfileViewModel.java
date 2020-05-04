package com.enyata.camdiary.ui.collections.collectorEditProfile;

import android.text.TextUtils;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.ChangePasswordRequest;
import com.enyata.camdiary.data.model.api.request.EditProfileRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class CollectorEditProfileViewModel extends BaseViewModel<CollectorEditProfileNavigator> {
    public CollectorEditProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
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
                    String firstname = response.getData().getFirstName();
                    String image_url = response.getData().getImageUrl();
                    String lastname = response.getData().getLastName();
                    String address = response.getData().getContactAddress();
                    String phoneNo = response.getData().getContactNo();
                    getDataManager().updateUserProfile(firstname,lastname,image_url,phoneNo,address);
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
