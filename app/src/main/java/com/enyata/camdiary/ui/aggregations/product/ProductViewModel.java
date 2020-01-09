package com.enyata.camdiary.ui.aggregations.product;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

public class ProductViewModel extends BaseViewModel<ProductNavigator> {
    public ProductViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onProduct(){
        getNavigator().product();
    }

    public void onBack(){
        getNavigator().back();
    }

    public void setCollectorId(String id){
        getDataManager().setCollectorCollectionId(id);
    }

    public String getCollectorId(){
        return getDataManager().getCollectorId();
    }

    public String getAggregationCollection(){
        return getDataManager().getAggregationCollection();
    }

    public boolean isVolumeEmpty(String volume){
        return TextUtils.isEmpty(volume);
    }

    public boolean checkIfAggregationCollectionIsNotEmpty(){
        return !getAggregationCollection().equals("nil");
    }

    public void setAggregationCollection(String collection){
        getDataManager().setAggregationCollection(collection);
    }

    public void getCollectorCollection(String id){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getCollectorCollection(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().getCollectorCollection(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    private MutableLiveData<CollectionResponse> collectionMutableLiveData = new MutableLiveData<>();

    public void setCollections(CollectionResponse collection) {
        collectionMutableLiveData.setValue(collection);
    }

    public LiveData<CollectionResponse> getCollections() {
        return collectionMutableLiveData;
    }

}
