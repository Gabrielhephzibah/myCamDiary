package com.enyata.camdiary.ui.aggregations.history;

import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;

public interface AggregatorHistoryNavigator {
    void handleError(Throwable throwable);
    void scan();
    void  back();
    void getAggregatorHistory(AggregationCollectionResponse response);
}
