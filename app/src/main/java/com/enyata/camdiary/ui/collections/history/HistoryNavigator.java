package com.enyata.camdiary.ui.collections.history;

import com.enyata.camdiary.data.model.api.response.CollectionResponse;

public interface HistoryNavigator {
    void handleError(Throwable throwable);
    void dataCollection();
    void scan();
    void back();
    void getAllCollections(CollectionResponse allCollections);

}
