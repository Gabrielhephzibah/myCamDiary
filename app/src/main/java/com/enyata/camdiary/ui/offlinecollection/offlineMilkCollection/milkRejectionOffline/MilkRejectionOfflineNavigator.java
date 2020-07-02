package com.enyata.camdiary.ui.offlinecollection.offlineMilkCollection.milkRejectionOffline;

import android.view.View;

import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface MilkRejectionOfflineNavigator {
    void  submit();
    void  back();
    void onCheckboxClicked(View view);
    void handleError(Throwable throwable);
    void onResponse();
}
