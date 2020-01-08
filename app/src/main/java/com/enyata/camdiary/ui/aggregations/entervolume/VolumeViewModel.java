package com.enyata.camdiary.ui.aggregations.entervolume;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import org.json.JSONArray;

public class VolumeViewModel extends BaseViewModel<VolumeNavigator> {
    public VolumeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAccept(){
        getNavigator().accept();
    }

    public  void  onBack(){
        getNavigator().back();
    }

    public void saveAggregationCollection(String collection){
        getDataManager().setAggregationCollection(collection);
    }

    public String getCollectorId(){
        return getDataManager().getCollectorId();
    }

    public String getCollectorName(){
        return getDataManager().getCollectorName();
    }
}
