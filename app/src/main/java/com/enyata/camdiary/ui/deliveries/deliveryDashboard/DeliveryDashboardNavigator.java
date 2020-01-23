package com.enyata.camdiary.ui.deliveries.deliveryDashboard;

import com.enyata.camdiary.data.model.api.response.DeliveryCompletedResponse;
import com.enyata.camdiary.data.model.api.response.Order;
import com.enyata.camdiary.data.model.api.response.PendingDeliveryResponse;
import com.enyata.camdiary.data.model.api.response.Product;

public interface DeliveryDashboardNavigator {
    void handleError(Throwable throwable);
    void createSliderDash(int current_position);
    void signup();
    void history();
    void delivery();

    void logout();
    void  getDeliveryCompleted(DeliveryCompletedResponse response);
    void  getPendingDelivery(PendingDeliveryResponse response);

}
