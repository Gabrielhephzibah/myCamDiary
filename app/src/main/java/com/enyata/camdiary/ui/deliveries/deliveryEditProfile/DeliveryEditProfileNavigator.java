package com.enyata.camdiary.ui.deliveries.deliveryEditProfile;

import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;

public interface DeliveryEditProfileNavigator {
    void onBack();
    void handleError(Throwable throwable);

    void onResponse(ResetPasswordResponse response);
    void onEditProfile(ResetPasswordResponse response);
}
