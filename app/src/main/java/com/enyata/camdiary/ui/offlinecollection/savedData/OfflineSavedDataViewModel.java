package com.enyata.camdiary.ui.offlinecollection.savedData;

import android.text.TextUtils;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.CommonUtils;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class OfflineSavedDataViewModel extends BaseViewModel<OfflineSavedDataNavigator> {
    public OfflineSavedDataViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
    public void onBackToDashboard(){
        getNavigator().onBackToDashboard();
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        return !TextUtils.isEmpty(email) && CommonUtils.isEmailValid(email) && !TextUtils.isEmpty(password);
    }

    public boolean isLengthEqualsToSeven(String password) {
        return password.length() >= 7;
    }

    public void getAllCdsData(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllCdsData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onGetResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }


    public void deleteCdsData(CdsDataCollection cdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .deleteCdsData(cdsDataCollection)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(() -> {
                    setIsLoading(false);
                    getNavigator().onDeleteResponse();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void deleteDataOnUpoad(CdsDataCollection cdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .deleteCdsData(cdsDataCollection)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(() -> {
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void getAllPdsData(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllPdsData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onGetPdsResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void deletePdsData(PdsDataCollection pdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .deletePdsData(pdsDataCollection)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(() -> {
                    setIsLoading(false);
                    getNavigator().onDeleteResponse();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void getAllBdsData(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllBdsData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onGetBdsResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void deleteBdsData(BdsDataCollections bdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .deleteBdsData(bdsDataCollection)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(() -> {
                    setIsLoading(false);
                    getNavigator().onDeleteResponse();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }



    public void loginToUpload(String email, String password){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .login(new CamLogin.Request(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    String accessToken = response.getData().getToken();
                    getDataManager().setAccessToken(accessToken);
                    setIsLoading(false);
                    getNavigator().onLoginResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().onLoginError(throwable);
                }));
    }

    public void loginToUploadPDS(String email, String password){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .login(new CamLogin.Request(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    String accessToken = response.getData().getToken();
                    getDataManager().setAccessToken(accessToken);
                    setIsLoading(false);
                    getNavigator().onLoginPDSResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().onLoginError(throwable);
                }));
    }




    public void getAllCdsDataAndUpload(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllCdsData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onGetCdsUploadResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }


    public Single<NewCollectionResponse>submitCdsData(CdsDataRequest.Request request){
      return getDataManager().submitCdsDataQuestion(request);
    }

    public Flowable<List<CdsDataCollection>> collection(){
        return  getDataManager().getAllCdsData();
    }

    public Single<Boolean> emptycheck(){
        return  collection().isEmpty();
    }

}
