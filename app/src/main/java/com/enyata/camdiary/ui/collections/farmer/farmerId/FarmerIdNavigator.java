package com.enyata.camdiary.ui.collections.farmer.farmerId;

import com.enyata.camdiary.data.model.api.response.DetailsResponse;
import com.enyata.camdiary.data.model.api.response.FarmerDetails;

public interface FarmerIdNavigator {
    void accept();
    void  onResponse(DetailsResponse details);
    void handleError(Throwable throwable);
}
