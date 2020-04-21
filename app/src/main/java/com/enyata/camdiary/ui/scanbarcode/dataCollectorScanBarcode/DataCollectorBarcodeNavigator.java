package com.enyata.camdiary.ui.scanbarcode.dataCollectorScanBarcode;

import com.enyata.camdiary.data.model.api.response.DetailsResponse;

public interface DataCollectorBarcodeNavigator {
    void handleError(Throwable throwable);
    void onResponse(DetailsResponse response);
}
