package com.enyata.camdiary.ui.offlinecollection.savedData;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.BdsDataRequest;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.request.NewCreateCollectionRequest;
import com.enyata.camdiary.data.model.api.request.PdsDataRequest;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.data.remote.ApiUtils;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.cdsoffline.CdsOfflineNavigator;
import com.enyata.camdiary.utils.CommonUtils;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class OfflineSavedDataViewModel extends BaseViewModel<OfflineSavedDataNavigator> {
    public OfflineSavedDataViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    CompositeDisposable disposable = new CompositeDisposable();
    private APIService mAPIService;
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

    public void deleteDataOnUpload(CdsDataCollection cdsDataCollection){
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


    public void deletePdsOnUpload(PdsDataCollection pdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .deletePdsData(pdsDataCollection)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(() -> {
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void deleteBdsOnUpload(BdsDataCollections bdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .deleteBdsData(bdsDataCollection)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(() -> {
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void deleteCollectionOnUpload(MilkCollection milkCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .deleteMilkCollectionData(milkCollection)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(() -> {
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().onSubmitCollectionError(throwable);
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

    public void deleteMilkCollection(MilkCollection milkCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .deleteMilkCollectionData(milkCollection)
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


    public void loginToUploadBDS(String email, String password){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .login(new CamLogin.Request(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    String accessToken = response.getData().getToken();
                    getDataManager().setAccessToken(accessToken);
                    setIsLoading(false);
                    getNavigator().onLoginBDSResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().onLoginError(throwable);
                }));
    }

    public void loginToUploadCollection(String email, String password){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .login(new CamLogin.Request(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    String accessToken = response.getData().getToken();
                    getDataManager().setAccessToken(accessToken);
                    setIsLoading(false);
                    getNavigator().onLoginCollectionResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().onLoginCollectionError(throwable);
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

    public void getAllPdsDataAndUpload(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllPdsData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onGetPdsUploadResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void getAllCollectionDataAndUpload(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllMilkCollectionData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onGetCollectionUploadResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void getAllBdsDataAndUpload(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllBdsData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onGetBdsUploadResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }


    public void getAllMilkCollection(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllMilkCollectionData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onMilkCollectionResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public void submitCdsDataCollection(CdsDataRequest.Request request, CdsDataCollection cdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .submitCdsDataQuestion(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onCdsUploadResponse(response, cdsDataCollection);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().onSubmitCdsDataError(throwable);
                }));

    }


    public void onSubmitBdsDataCollection(BdsDataRequest.Request request, BdsDataCollections bdsDataCollections){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .submitBdsDataQuestion(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onBdsUploadResponse(response,bdsDataCollections);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().onSubmitCdsDataError(throwable);
                }));

    }


    public void submitPdsDataCollection(PdsDataRequest.Request request, PdsDataCollection pdsDataCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .submitPdsDataQuestion(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onPdsUploadResponse(response,pdsDataCollection);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().onSubmitCdsDataError(throwable);
                }));

    }


    public void createMilkCollection(NewCreateCollectionRequest request, MilkCollection milkCollection) {
        mAPIService = ApiUtils.getAPIService();
        getCompositeDisposable().add(
                mAPIService.createNewCollection(getDataManager().getAccessToken(),request )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            getNavigator().onMilkCollectionUploadResponse(response, milkCollection);
//                            Intent i = new Intent(getApplicationContext(), ResponseActivity.class);
//                            startActivity(i);
                            Log.i("RESPONSE","RESPONSE IS SUCESSFULK");
                        },throwable -> {
                            Log.i("Error","ERRROR");
                            getNavigator().onSubmitCollectionError(throwable);

//
                        }));
    }


    public String getCurrentDate(){
        return (String) DateFormat.format("dd-MM-yyyy", new java.util.Date());
    }

    public Single<NewCollectionResponse>submitCdsData(CdsDataRequest.Request request){
      return getDataManager().submitCdsDataQuestion(request);
    }

    public Flowable<List<CdsDataCollection>> collection(){
        return  getDataManager().getAllCdsData();
    }

    public Single<NewCollectionResponse>submitPDSData(PdsDataRequest.Request request){
        return getDataManager().submitPdsDataQuestion(request);
    }

    public Single<NewCollectionResponse>submitBDSData(BdsDataRequest.Request request){
        return  getDataManager().submitBdsDataQuestion(request);
    }
    public String getAccesstoken(){
        return  getDataManager().getAccessToken();
    }

    public boolean checkifCdsIsEmpty() {
        return getCdsData() == null;
    }

    public boolean checkIfBdsIsEmpty(){return  getBdsData() == null; }

    public  boolean checkIfPdsIsEmpty(){return  getPdsData() == null;}

    public  boolean checkIfMilkCollectionIsEmpty(){return getMilkCollectionData() == null;}


    public Flowable<List<CdsDataCollection>>getCdsData() {
        return getDataManager().getAllCdsData();
    }


    public Flowable<List<BdsDataCollections>>getBdsData() {
        return getDataManager().getAllBdsData();
    }

    public Flowable<List<PdsDataCollection>>getPdsData() {
        return getDataManager().getAllPdsData();
    }

    public Flowable<List<MilkCollection>>getMilkCollectionData() {
        return getDataManager().getAllMilkCollectionData();
    }


    public void dispose(){
        onCleared();
    }



}
