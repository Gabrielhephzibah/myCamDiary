package com.enyata.camdiary.ui.aggregations.aggregatorEditProfile;

import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;

public interface AggregatorEditProfileNavigator {
    void  onBack();
    void onResponse(ResetPasswordResponse response);
    void handleError(Throwable throwable);
    void onEditProfile(ResetPasswordResponse response);
}
