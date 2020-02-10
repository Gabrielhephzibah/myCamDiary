package com.enyata.camdiary.ui.deliveries.history;

import com.enyata.camdiary.data.model.api.response.DeliveryHistoryResponseData;

public interface DeliveryHistoryNavigator {

    void signup();

    void delivery();

    void logout();

    void back();

    void deliveryHistory(DeliveryHistoryResponseData response);

    void handleError(Throwable throwable);

}
