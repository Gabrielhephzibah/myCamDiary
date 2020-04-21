package com.enyata.camdiary.ui.scanbarcode.aggregatorScanBarCode;

import com.enyata.camdiary.data.model.api.response.CollectorDetailsResponse;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;

public interface AggregatorBarcodeNavigator {
    void handleError(Throwable throwable);
    void onResponse(CollectorDetailsResponse response);

}
