package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.pdsoffline;

import com.enyata.camdiary.data.model.db.PdsDataCollection;

import java.util.List;

public interface PdsOfflineNavigator {
    void  onIncomeSource();

    void onFarmInfo();

    void onSubmitPds();

    void onBack();

    void onBioData();

    void onResponse();

    void handleError(Throwable throwable);




}
