package com.enyata.camdiary.ui.offlinecollection.offlineMilkCollection.milkRejectionOffline;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import java.util.List;

public class MilkRejectionOfflineViewModel extends BaseViewModel<MilkRejectionOfflineNavigator> {
    public MilkRejectionOfflineViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onSubmit(){
        getNavigator().submit();
    }

    public  void onBack(){
        getNavigator().back();
    }

    public String getOfflineFarmerId(){
        return getDataManager().getOfflineFarmerId();
    }

    public void addNewMilkCollection(MilkCollection milkCollection){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .addNewMilkCollectionData(milkCollection)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(() -> {
                    setIsLoading(false);
                    getNavigator().onResponse();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));

    }

    public List<ChurnDetailsData> getChurnDetails(){
        return getDataManager().getChurnDetails();
    }

    public void  deleteChurnDetails(List<ChurnDetailsData> churnDetails) {
        getDataManager().deleteChurnDetails(churnDetails);
    }


}
