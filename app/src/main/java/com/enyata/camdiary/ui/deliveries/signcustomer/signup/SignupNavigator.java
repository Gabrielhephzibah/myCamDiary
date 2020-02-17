package com.enyata.camdiary.ui.deliveries.signcustomer.signup;

import com.enyata.camdiary.data.model.api.response.DispatcherSignUpResponse;

public interface SignupNavigator {
    void submit();

    void logout();

    void onBack();

    void dispatcherSignup(DispatcherSignUpResponse response);

    void handleError(Throwable throwable);

    void history();



}
