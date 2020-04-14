package com.enyata.camdiary.ui.collections.data.pdsData;

import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;

public interface PdsDataNavigator {
    void  onIncomeSource();

    void onFarmInfo();

    void onSubmitPds();

    void onBack();
    void onResponse(NewCollectionResponse response);
    void handleError(Throwable throwable);

}
