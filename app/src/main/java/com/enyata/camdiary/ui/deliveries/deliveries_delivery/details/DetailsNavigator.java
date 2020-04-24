package com.enyata.camdiary.ui.deliveries.deliveries_delivery.details;

import com.enyata.camdiary.data.model.api.response.DeliveryDetailResponse;

public interface DetailsNavigator {
    void back();
    void deliver();
    void onResponse(DeliveryDetailResponse response);
    void handleError(Throwable throwable);
}
