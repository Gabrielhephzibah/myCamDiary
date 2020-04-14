package com.enyata.camdiary.ui.collections.data.dataFarmerId;

import com.enyata.camdiary.data.model.api.response.DetailsResponse;

public interface DataFarmerIdNavigator {
    void onAccept();
    void onBack();
    void  onResponse(DetailsResponse details);
    void handleError(Throwable throwable);
}
