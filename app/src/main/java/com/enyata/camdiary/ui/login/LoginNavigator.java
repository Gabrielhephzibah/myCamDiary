

package com.enyata.camdiary.ui.login;

/**
 * Created by Sanni Michael on 10/12/2019.
 */

public interface LoginNavigator {

    void handleError(Throwable throwable);

    void loginClick();

    void goToDashBoard(String type);

}

