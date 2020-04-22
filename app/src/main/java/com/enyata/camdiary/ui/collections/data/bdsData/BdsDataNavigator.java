package com.enyata.camdiary.ui.collections.data.bdsData;

import com.enyata.camdiary.data.model.api.response.ElectoralWardResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface BdsDataNavigator {
    void onBioData();
    void onLocationInfo();
    void onIncomeSource();
    void  onCoperativeInfo();
    void  onFarmInfo();
    void onBack();
    void onSubmitBds();
    void onUploadPicture();
    void onResponse(NewCollectionResponse response);
    void handleError(Throwable throwable);
    void onElectoralWardResponse(ElectoralWardResponse response);
}
