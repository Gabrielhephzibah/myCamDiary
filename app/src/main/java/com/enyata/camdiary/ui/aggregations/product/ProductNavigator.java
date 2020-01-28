package com.enyata.camdiary.ui.aggregations.product;

import com.enyata.camdiary.data.model.NewResponse;
import com.enyata.camdiary.data.model.api.request.Aggregation;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.api.response.SavedAggregationResponse;

import retrofit2.Response;

public interface ProductNavigator {
    void handleError(Throwable throwable);
    void product();
    void back();
    void getCollectorCollection(CollectionResponse response);
    void onResponse();
    void onFailed(Throwable throwable);

}
