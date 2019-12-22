package com.enyata.camdiary.ui.aggregations.product;

import com.enyata.camdiary.data.DataManager;
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

    public  void  getCollectorCollection(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getCollectorCollection()
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
}
