package com.enyata.camdiary.ui.aggregations.history;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class AggregatorHistoryViewModel extends BaseViewModel<AggregatorHistoryNavigator> {

    public AggregatorHistoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void onScancode(){
        getNavigator().scan();
    }

    public  void  onBack(){
        getNavigator().back();
    }


    public  void  getAggregationHistory(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAggregationHistory()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response ->{
                    setIsLoading(false);
                    getNavigator().getAggregationHistory(response);
                },throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }


    public  void  dispose(){
        onCleared();
    }

    public void onLogOut(){
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().onLogOut();
    }

    public  String getCurrentUser(){
       return getDataManager().getCurrentUserName();
    }
}
