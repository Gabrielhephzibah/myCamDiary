package com.enyata.camdiary.ui.collections.dashboard;

import com.enyata.camdiary.data.model.api.response.AllEntries;
import com.enyata.camdiary.data.model.api.response.CollectionResponse;
import com.enyata.camdiary.data.model.api.response.VolumeResponse;

public interface DashboardNavigator {
    void handleError(Throwable throwable);
    void createSliderDash(int current_position);
    void scancode();
    void history();
    void dataCollection();
    void logout();
    void displayAcceptedVolume(VolumeResponse volume);
    void displayRejectedVolume(VolumeResponse volume);
    void getAllEntries(AllEntries entries);
    void getTodayCollection(CollectionResponse todayCollectionResponse);
}
