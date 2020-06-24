package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.bdsoffline;

import com.enyata.camdiary.data.model.db.BdsDataCollections;

import java.util.List;

public interface BdsOfflineNavigator {
    void onBioData();
    void onLocationInfo();
    void onIncomeSource();
    void  onCoperativeInfo();
    void  onFarmInfo();
    void onBack();
    void onUploadPicture();
    void onSubmitBds();
    void onResponse();
    void handleError(Throwable throwable);
    void onGetResponse(List<BdsDataCollections> bdsDataCollection);
}
