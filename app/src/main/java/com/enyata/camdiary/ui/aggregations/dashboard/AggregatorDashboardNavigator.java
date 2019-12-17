package com.enyata.camdiary.ui.aggregations.dashboard;

import com.enyata.camdiary.data.model.api.response.AggregationVolume;
import com.enyata.camdiary.data.model.api.response.NoOfCollectors;

public interface AggregatorDashboardNavigator {
    void handleError(Throwable throwable);
    void history();
    void scan();
    void out();
    void  createSliderDash(int current_position);
    void displayAggregatorVolume(AggregationVolume volume);
    void noOfCollectors(NoOfCollectors aggregation);

}
