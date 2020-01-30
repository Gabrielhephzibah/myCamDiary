package com.enyata.camdiary.ui.password;

import com.enyata.camdiary.data.model.api.response.ResetPasswordResponse;

public interface ResetPasswordNavigator  {
    void handleError(Throwable throwable);
    void onBack();

    void onSubmit();

    void onResetResponse(ResetPasswordResponse response);

}
