package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editCdsOffline;

public interface EditCdsOfflineNavigator {
    void onBioData();
    void onLocationInfo();
    void onIncomeSources();
    void onFarmInfo();
    void onBack();
    void onSubmitCDS();
    void onResponse();
    void handleError(Throwable throwable);
}
