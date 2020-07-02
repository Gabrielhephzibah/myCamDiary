package com.enyata.camdiary.ui.offlinecollection.savedData;

import com.enyata.camdiary.data.model.api.request.CdsDataRequest;
import com.enyata.camdiary.data.model.api.response.CamLoginResponse;
import com.enyata.camdiary.data.model.api.response.NewCollectionResponse;
import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;

import java.util.List;

public interface OfflineSavedDataNavigator {
    void onBackToDashboard();
    void handleError(Throwable throwable);
    void onGetResponse(List<CdsDataCollection> cdsDataCollection);
    void onDeleteResponse();
    void  onGetPdsResponse(List<PdsDataCollection>pdsDataCollections);
   void onGetBdsResponse(List<BdsDataCollections>bdsDataCollections);

    void onLoginResponse(CamLoginResponse response);
    void onLoginError(Throwable throwable);
    void onLoginCollectionError(Throwable throwable);

    void onGetCdsUploadResponse(List<CdsDataCollection> cdsDataCollections);
    void onGetPdsUploadResponse(List<PdsDataCollection> pdsDataCollections);
    void onGetBdsUploadResponse(List<BdsDataCollections> bdsDataCollections);
    void onGetCollectionUploadResponse(List<MilkCollection> milkCollections);

    void  onSubmitCdsData(NewCollectionResponse cdsDataRequest);

    void onSubmitCdsDataError(Throwable throwable);
    void  onSubmitCollectionError(Throwable throwable);
    void onLoginPDSResponse(CamLoginResponse response);
    void onLoginBDSResponse(CamLoginResponse response);
    void onLoginCollectionResponse(CamLoginResponse response);

    void onMilkCollectionResponse(List<MilkCollection> milkCollections);



}
