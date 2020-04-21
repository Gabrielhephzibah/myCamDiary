package com.enyata.camdiary.ui.aggregations.milkcollection;

import com.enyata.camdiary.data.model.api.response.CollectorDetails;
import com.enyata.camdiary.data.model.api.response.MilkCollectionDataResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface MilkCollectionNavigator {


    void onBack();

    void onProceed();

    void handleError(Throwable throwable);

    void getMilkCollectionData(MilkCollectionDataResponse response);

    void  onAggregationResponse(NewCollectionResponse response);

    void aggregationHandleError(Throwable throwable);


}
