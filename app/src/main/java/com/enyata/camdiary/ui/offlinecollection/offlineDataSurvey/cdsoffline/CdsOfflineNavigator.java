package com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.cdsoffline;

import com.enyata.camdiary.data.model.db.CdsDataCollection;

import java.util.List;

public interface CdsOfflineNavigator {
    void onBioData();
    void onLocationInfo();
    void onIncomeSources();
    void onFarmInfo();
    void onBack();
    void onSubmitCDS();
    void onResponse();
    void handleError(Throwable throwable);

}
