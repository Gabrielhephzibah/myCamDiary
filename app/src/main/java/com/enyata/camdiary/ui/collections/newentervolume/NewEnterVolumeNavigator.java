package com.enyata.camdiary.ui.collections.newentervolume;

import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface NewEnterVolumeNavigator {
    void accept();

    void reject();
    void back();
    void handleError(Throwable throwable);
    void displayResponse(NewCollectionResponse response);
    void dismissAllModal();
    void onStarting();
    void onResponse(NewCollectionResponse response);
}
