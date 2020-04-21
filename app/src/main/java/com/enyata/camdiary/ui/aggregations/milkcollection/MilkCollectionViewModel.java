package com.enyata.camdiary.ui.aggregations.milkcollection;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.request.CreateAggregationRequest;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class MilkCollectionViewModel extends BaseViewModel<MilkCollectionNavigator> {
    public MilkCollectionViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }



    public void onBack(){
        getNavigator().onBack();
    }

    public void onProceed(){
        getNavigator().onProceed();
    }

    public String getCollectorId(){
        return getDataManager().getCollectorId();
    }

    public void setCollectorName(String  collectorName){
        getDataManager().setCollectorName(collectorName);
    }

    public String getCollectorName(){
        return  getDataManager().getCollectorName();
    }

    public void  onDispose(){
        onCleared();
    }

    public  void  getMilkCollectionData(String collectorId){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getMilkCollectionData(collectorId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().getMilkCollectionData(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void createAggregation(CreateAggregationRequest.Request request){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .createAggregation(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().onAggregationResponse(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().aggregationHandleError(throwable);
                }));
    }
}
