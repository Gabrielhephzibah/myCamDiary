package com.enyata.camdiary.ui.aggregations.dashboard;

import com.enyata.camdiary.data.model.api.response.AggregationCollectionResponse;
import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.AggregatorCollections;
import com.enyata.camdiary.data.model.api.response.NumberOfCollectors;

public interface AggregatorDashboardNavigator {
    void handleError(Throwable throwable);
    void history();
    void scan();
    void out();
    void  createSliderDash(int current_position);
    void displayAggregatorVolume(AggregationVolume volume);
    void numberOfCollectors(NumberOfCollectors aggregation);
    void  getAggregatorTodayCollection(AggregationCollectionResponse todayCollection);

}
