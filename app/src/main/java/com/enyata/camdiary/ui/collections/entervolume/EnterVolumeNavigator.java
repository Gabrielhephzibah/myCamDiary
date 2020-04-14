package com.enyata.camdiary.ui.collections.entervolume;

import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface EnterVolumeNavigator {
    void accept();
    void onResponseError();
    void reject();
    void back();
    void handleError(Throwable throwable);
    void displayResponse(NewCollectionResponse response);
    void dismissAllModal();
}
