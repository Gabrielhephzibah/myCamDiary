package com.enyata.camdiary.ui.collections.rejection.reason;

import android.view.View;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.Collection;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class ReasonViewModel extends BaseViewModel<ReasonNavigator> {

    public ReasonViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

   public void onSubmit(){
        getNavigator().submit();
   }

   public  void onBack(){
        getNavigator().back();
   }

  public  void createRejectionCollection(JSONObject params){
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
                  getNavigator().onResponse(response);
              }, throwable -> {
                  setIsLoading(false);
                  getNavigator().handleError(throwable);
              }));


  }

    public void dispose(){
        onCleared();
    }

    public String getFarmerId(){
        return getDataManager().getFarmerId();
    }
}
