package com.enyata.camdiary.ui.aggregations.product;

import com.enyata.camdiary.data.model.api.request.Aggregation;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.SavedAggregationResponse;

public interface ProductNavigator {
    void handleError(Throwable throwable);
    void product();
    void back();
    void getCollectorCollection(CollectionResponse response);
    void responseMessage(SavedAggregationResponse response);
    void aggregationCollection(Aggregation aggregate);


}
