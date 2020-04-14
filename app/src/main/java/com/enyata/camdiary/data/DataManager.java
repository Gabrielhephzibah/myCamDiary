

package com.enyata.camdiary.data;

import com.enyata.camdiary.data.local.db.DbHelper;
import com.enyata.camdiary.data.local.prefs.PreferencesHelper;
import com.enyata.camdiary.data.remote.ApiHelper;


/**
 * Created by Sanni Michael 10/12/2019
 */

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void setUserAsLoggedOut();

    void updateApiHeader(Long userId, String accessToken);

    void updateUserInfo(
            String accessToken,
            String firstname,
            String lastname,
            String usertype,
            String email, String imageUrl, String phoneNo,
            String address);

    void updateLoginStatus(
            LoggedInMode loggedInMode);


    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_LOGGED_IN(1);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
