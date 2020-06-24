package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editBdsOffline;

public interface EditBdsOfflineNavigator {
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
}
