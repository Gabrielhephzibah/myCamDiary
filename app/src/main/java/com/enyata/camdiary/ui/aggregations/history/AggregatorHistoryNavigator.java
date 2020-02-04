package com.enyata.camdiary.ui.aggregations.history;

import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationHistoryResponse;

public interface AggregatorHistoryNavigator {
    void handleError(Throwable throwable);
    void scan();
    void  back();
    void onLogOut();
    void getAggregationHistory(AggregationHistoryResponse response);
}
