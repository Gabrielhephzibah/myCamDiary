package com.enyata.camdiary.ui.aggregations.product;

import com.enyata.camdiary.data.model.api.response.CollectionResponse;

public interface ProductNavigator {
    void handleError(Throwable throwable);
    void product();
    void back();
    void getCollectorCollection(CollectionResponse response);
}
