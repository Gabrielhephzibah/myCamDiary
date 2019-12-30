package com.enyata.camdiary.ui.aggregations.dashboard;

import android.text.format.DateFormat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class AggregatorDashboardViewModel extends BaseViewModel<AggregatorDashboardNavigator> {
    public AggregatorDashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public  void  onHistory(){
        getNavigator().history();
    }

    public  void  onScancode(){
        getNavigator().scan();
    }

    public void onLogout(){
        getDataManager().updateLoginStatus(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        getNavigator().onLogOut();

    }


    public void  getTotalVolumeCollectedByAggregator(){
        getCompositeDisposable().add(getDataManager()
                .getAggregationVolume()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().displayAggregatorVolume(response);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }


    public void getTotalNumberOfCollectors(){
        getCompositeDisposable().add(getDataManager()
                .getTotalAggregation()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().numberOfCollectors(response);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));
    }


    public void  getAggregatorTodayCollection(){
        getCompositeDisposable().add(getDataManager()
        .getAggregatorTodaysCollection()
        .subscribeOn(getSchedulerProvider().io())
         .observeOn(getSchedulerProvider().ui())
         .subscribe(response ->{
             getNavigator().getAggregatorTodayCollection(response);
         },throwable -> {
             getNavigator().handleError(throwable);

         }));

    }



    public String getCurrentDate(){
        return (String) DateFormat.format("dd/MM/yyyy", new java.util.Date());
    }
    public String getFirstName(){
        return getDataManager().getCurrentUserName();
    }



    /**
     * Live Data Instance
     */
    private MutableLiveData<String> aggregatorVolume = new MutableLiveData<>();
    private MutableLiveData<String> totalAggregation  = new MutableLiveData<>();


    public void setAggregatorVolume(String volume) {
        aggregatorVolume.setValue(volume);
    }

    public LiveData<String> getAggregatorVolume() {
        return aggregatorVolume;
    }

    public  void  setTotalAggregation(String numberOfCollectors){
        totalAggregation.setValue(numberOfCollectors);
    }



    public LiveData<String>getTotalAggregation(){
        return totalAggregation;
    }


}
