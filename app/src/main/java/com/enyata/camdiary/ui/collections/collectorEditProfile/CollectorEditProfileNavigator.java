package com.enyata.camdiary.ui.collections.collectorEditProfile;

import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;

public interface CollectorEditProfileNavigator {
    void onBack();

    void handleError(Throwable throwable);

    void onResponse(ResetPasswordResponse response);
    void onEditProfile(ResetPasswordResponse response);
}
