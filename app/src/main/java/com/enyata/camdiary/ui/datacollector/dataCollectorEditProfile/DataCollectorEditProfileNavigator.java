package com.enyata.camdiary.ui.datacollector.dataCollectorEditProfile;

import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;

public interface DataCollectorEditProfileNavigator {

    void onBack();

    void handleError(Throwable throwable);

    void onResponse(ResetPasswordResponse response);
    void onEditProfile(ResetPasswordResponse response);
}
