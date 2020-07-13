package com.enyata.camdiary.ui.aggregations.collectorCollection;

import com.enyata.camdiary.data.model.api.response.ChurnDetails;
import com.enyata.camdiary.data.model.api.response.CollectorCollectionResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface CollectorCollectionNavigator {
    void onViewMore();
    void onCollection();
    void onResponse(CollectorCollectionResponse response);
    void handleError(Throwable throwable);
    void onBack();
    void onChurnDetailsResponse(ChurnDetails response);
    void onAggregationResponse(NewCollectionResponse response, int position);
    void onStarting();
    void AggregationError(Throwable throwable);

}
