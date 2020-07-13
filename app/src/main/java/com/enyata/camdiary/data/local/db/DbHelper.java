
package com.enyata.camdiary.data.local.db;


import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Sanni Michael on 10/12/2019
 */

public interface DbHelper {

    Flowable<List<CdsDataCollection>>getAllCdsData();

    Completable addNewCdsData(CdsDataCollection cdsDataCollection);

    Completable deleteCdsData(CdsDataCollection cdsDataCollection);

    Completable updateCdsData(CdsDataCollection cdsDataCollection);

    Flowable<List<PdsDataCollection>>getAllPdsData();

    Completable addNewPdsData(PdsDataCollection pdsDataCollection);

    Completable deletePdsData(PdsDataCollection pdsDataCollection);

    Completable updatePdsData(PdsDataCollection pdsDataCollection);

    Flowable<List<BdsDataCollections>>getAllBdsData();

    Completable addNewBdsData(BdsDataCollections bdsDataCollection);

    Completable deleteBdsData(BdsDataCollections bdsDataCollection);

    Completable updateBdsData(BdsDataCollections bdsDataCollection);

    Flowable<List<MilkCollection>>getAllMilkCollectionData();

    Completable addNewMilkCollectionData(MilkCollection milkCollection);

    Completable deleteMilkCollectionData(MilkCollection milkCollection);

    List<CdsDataCollection>getCdsDataAndUpload();





}
