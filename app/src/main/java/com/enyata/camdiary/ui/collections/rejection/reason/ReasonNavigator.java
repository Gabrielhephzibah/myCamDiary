package com.enyata.camdiary.ui.collections.rejection.reason;

import android.view.View;

import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface ReasonNavigator {
    void  submit();
    void  back();
    void onCheckboxClicked(View view);
    void handleError(Throwable throwable);
    void onResponse(NewCollectionResponse response);
}
