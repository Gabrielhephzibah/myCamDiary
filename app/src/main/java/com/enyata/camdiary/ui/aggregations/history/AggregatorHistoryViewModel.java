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

    public void  getAggretionHistory(){
        getCompositeDisposable().add(getDataManager()
                .getAggregatorHistory()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response ->{
                    getNavigator().getAggregatorHistory(response);
                },throwable -> {
                    getNavigator().handleError(throwable);

                }));

    }
    public  void  dispose(){
        onCleared();
    }
}
