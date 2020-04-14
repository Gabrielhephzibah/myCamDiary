package com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode;

import com.enyata.camdiary.data.model.api.response.DetailsErrorMessage;
import com.enyata.camdiary.data.model.api.response.DetailsResponse;

public interface CollectorBarcodeNavigator  {
    void handleError(Throwable throwable);
    void onResponse(DetailsResponse response);
}
