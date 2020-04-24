package com.enyata.camdiary.ui.collections.data.cdsData;

import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;
import com.enyata.camdiary.data.model.api.response.ElectoralWardResponse;
import com.enyata.camdiary.data.model.api.response.GetCoperativeNameResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface CdsNavigator {

    void onBioData();
    void onLocationInfo();
    void onIncomeSources();
    void onFarmInfo();
    void onBack();
    void onResponse(NewCollectionResponse response);
    void handleError(Throwable throwable);
    void onElectoralWardResponse(ElectoralWardResponse response);


}
