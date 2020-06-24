package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editPdsOffline;

public interface EditPdsOfflineNavigator {
    void  onIncomeSource();

    void onFarmInfo();

    void onSubmitPds();

    void onBack();

    void onBioData();

    void onResponse();

    void handleError(Throwable throwable);
}
