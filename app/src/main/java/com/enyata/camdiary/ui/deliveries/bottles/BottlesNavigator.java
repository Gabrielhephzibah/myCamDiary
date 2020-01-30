package com.enyata.camdiary.ui.deliveries.bottles;

import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface BottlesNavigator {
    void finish();
    void onResponse(NewCollectionResponse response);
    void handleError(Throwable throwable);
    void onBack();
}
