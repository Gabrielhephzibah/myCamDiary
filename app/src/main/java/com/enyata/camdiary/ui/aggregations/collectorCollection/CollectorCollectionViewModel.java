package com.enyata.camdiary.ui.aggregations.collectorCollection;

import android.support.v4.app.INotificationSideChannel;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.NewAggregationRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class CollectorCollectionViewModel extends BaseViewModel<CollectorCollectionNavigator> {
    public CollectorCollectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

//    public void onViewMore(){
//        getNavigator().onViewMore();
//    }
//
//    public void onCollection(){
//        getNavigator().onCollection();
//    }

    public void getCollectorCollection(String collectorVerificationId){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .GetCollectorCollections(collectorVerificationId)
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

    public void getChurnDetails(String collectorVerificationId, String churnId){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getChurnDetails(collectorVerificationId,churnId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onChurnDetailsResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }


    public void createAggregation(NewAggregationRequest.Request request,int position){
      getNavigator().onStarting();
        getCompositeDisposable().add(getDataManager()
                .newCreateAggregation(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onAggregationResponse(response,position);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().AggregationError(throwable);
                }));
    }



    public String getCollectorVerificationId(){
        return getDataManager().getCollectorVerificationId();
    }

    public void onBack(){
        getNavigator().onBack();
    }

    public void dispose(){
        onCleared();
    }

    public String getCollectorName(){
        return getDataManager().getCollectorName();
    }
}
