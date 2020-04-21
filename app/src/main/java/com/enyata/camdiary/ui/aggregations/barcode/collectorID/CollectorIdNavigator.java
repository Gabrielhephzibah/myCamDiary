package com.enyata.camdiary.ui.aggregations.barcode.collectorID;

import com.enyata.camdiary.data.model.api.response.CollectorDetails;
import com.enyata.camdiary.data.model.api.response.CollectorDetailsResponse;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;

public interface CollectorIdNavigator {
    void handleError(Throwable throwable);
    void  accept();
    void  back();
    void  getCollectorDetails(CollectorDetailsResponse response);
}
