

package com.enyata.camdiary.ui.login;

import com.enyata.camdiary.data.model.api.response.CamLoginResponse;

/**
 * Created by Sanni Michael on 10/12/2019.
 */

public interface LoginNavigator {

    void handleError(Throwable throwable);

    void loginClick();

    void goToDashBoard(String type);

    void onForgotPassword();

    void onResponse(CamLoginResponse response);



}

