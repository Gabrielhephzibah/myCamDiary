package com.enyata.camdiary.ui.aggregations.product;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.AggregationSavedCollection;
import com.enyata.camdiary.data.model.NewResponse;
import com.enyata.camdiary.data.model.Post;
import com.enyata.camdiary.data.model.api.request.Aggregation;
import com.enyata.camdiary.data.model.api.request.AggregationCollection;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.remote.APIService;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public boolean isVolumeEmpty(String volume)
    {
        return TextUtils.isEmpty(volume);
    }


    public boolean checkIfAggregationCollectionIsNotEmpty() {
        return true;
    }
    public boolean checkIfAggregationCollectionIsEmpty(){
        return getAggregationCollectionList() == null;

    }

    public void setAggregationCollection(String collection){
        getDataManager().setAggregationCollection(collection);
    }

    public void setAggregationCollectionList(List<AggregationSavedCollection> list){
        getDataManager().saveAggregationCollectionList(list);
    }

    public List<AggregationSavedCollection> getAggregationCollectionList(){
        return getDataManager().getAggregationCollectionList();
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


//    public void sendPost(Post post) {
//
//
//        mAPIService.savePost(getDataManager().getAccessToken(),post).enqueue(new Callback<NewResponse>() {
//
//            @Override
//            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
//
//                if (response.isSuccessful()) {
////                    Post object = response.body();
////                    String jsonString = object.toString();
//
////                    showResponse(response.body().toString());
//                    Log.i("SUCCESS INFORMATION", "post submitted to API." + response.body().toString());
//
//                    getNavigator().Onresponse(response);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewResponse> call, Throwable t) {
//                Log.e("failure", "Unable to submit post to API.");
//            }
//        });
//    }

    public String getCollectorName(){
       return getDataManager().getCollectorName();

    }
    private APIService mAPIService;

    public String getAccessToken(){
        return getDataManager().getAccessToken();
    }

//    public void sendPost(String collectorId, List<AggregationSavedCollection>aggregation) {
//
//        mAPIService.savePost(collectorId, aggregation).enqueue(new Callback<Post>() {
//
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//
//                if(response.isSuccessful()) {
////                    showResponse(response.body().toString());
//                    Log.i("SUCCESS INFORMATION", "post submitted to API." + response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//                Log.e("failure", "Unable to submit post to API.");
//            }
//        });
//    }



    private MutableLiveData<CollectionResponse> collectionMutableLiveData = new MutableLiveData<>();

    public void setCollections(CollectionResponse collection) {
        collectionMutableLiveData.setValue(collection);
    }

    public LiveData<CollectionResponse> getCollections() {
        return collectionMutableLiveData;
    }

}
