package com.enyata.camdiary.ui.collections.farmer.farmerId;

import com.enyata.camdiary.data.model.api.response.DetailsResponse;

public interface FarmerIdNavigator {
    void accept();
    void back();
    void  onResponse(DetailsResponse details);
    void handleError(Throwable throwable);
}
