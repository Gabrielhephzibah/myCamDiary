package com.enyata.camdiary.ui.collections.entervolume;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.CamLogin;
import com.enyata.camdiary.data.model.api.request.Collection;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import org.json.JSONObject;

public class EnterVolumeViewModel extends BaseViewModel<EnterVolumeNavigator> {
    public EnterVolumeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAccept() {
        getNavigator().accept();
    }


    public void onReject() {
        getNavigator().reject();
    }

    public void onBack() {
        getNavigator().back();
    }

    public void createCollection(JSONObject params) {
        String farmerId = params.optString("farmer_id");
        String statusOfCollection = params.optString("status_of_collection");
        String volume = params.optString("volume");
        String testOne = params.optString("test_one");
        String testTwo = params.optString("test_two");
        String testThree = params.optString("test_three");
        String approvedContainer = params.optString("approved_container");
        String message = params.optString("message");

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doCreateCollection(new Collection.Request(farmerId, statusOfCollection, volume, testOne, testTwo, testThree, approvedContainer, message))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().displayResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                    getNavigator().onResponseError();
                }));
    }

    public void dispose(){
        onCleared();
    }

    public String getFarmerId(){
        return getDataManager().getFarmerId();
    }
}
