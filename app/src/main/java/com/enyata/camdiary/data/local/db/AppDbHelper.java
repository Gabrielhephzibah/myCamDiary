

package com.enyata.camdiary.data.local.db;


import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;


/**
 * Created by Sanni Michael on 10/12/2019
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final CamDiaryDataBase mAppDatabase;

    @Inject
    public AppDbHelper(CamDiaryDataBase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Flowable<List<CdsDataCollection>> getAllCdsData() {
        return mAppDatabase.cdsDao().getAllCdsDta();
    }

    @Override
    public Completable addNewCdsData(CdsDataCollection cdsDataCollection) {
        return mAppDatabase.cdsDao().addCdsData(cdsDataCollection);
    }

    @Override
    public Completable deleteCdsData(CdsDataCollection cdsDataCollection) {
        return mAppDatabase.cdsDao().deleteCdsData(cdsDataCollection);
    }

    @Override
    public Completable updateCdsData(CdsDataCollection cdsDataCollection) {
        return mAppDatabase.cdsDao().updateCdsData(cdsDataCollection);
    }

    @Override
    public Flowable<List<PdsDataCollection>> getAllPdsData() {
        return mAppDatabase.pdsDao().getAllPdsData();
    }

    @Override
    public Completable addNewPdsData(PdsDataCollection pdsDataCollection) {
        return mAppDatabase.pdsDao().addPdsData(pdsDataCollection);
    }

    @Override
    public Completable deletePdsData(PdsDataCollection pdsDataCollection) {
        return mAppDatabase.pdsDao().deletePdsData(pdsDataCollection);
    }

    @Override
    public Completable updatePdsData(PdsDataCollection pdsDataCollection) {
        return mAppDatabase.pdsDao().updatePdsData(pdsDataCollection);
    }

    @Override
    public Flowable<List<BdsDataCollections>> getAllBdsData() {
        return mAppDatabase.bdsDao().getAllBdsDta();
    }

    @Override
    public Completable addNewBdsData(BdsDataCollections bdsDataCollection) {
        return mAppDatabase.bdsDao().addBdsData(bdsDataCollection);
    }

    @Override
    public Completable deleteBdsData(BdsDataCollections bdsDataCollection) {
        return mAppDatabase.bdsDao().deleteBdsData(bdsDataCollection);
    }

    @Override
    public Completable updateBdsData(BdsDataCollections bdsDataCollection) {
        return mAppDatabase.bdsDao().updateBdsData(bdsDataCollection);
    }

    @Override
    public Flowable<List<MilkCollection>> getAllMilkCollectionData() {
        return mAppDatabase.milkDao().getAllMilkCollection();
    }

    @Override
    public Completable addNewMilkCollectionData(MilkCollection milkCollection) {
        return mAppDatabase.milkDao().addMilkCollection(milkCollection);
    }

    @Override
    public Completable deleteMilkCollectionData(MilkCollection milkCollection) {
        return mAppDatabase.milkDao().deleteMilkCollection(milkCollection);
    }

    @Override
    public List<CdsDataCollection> getCdsDataAndUpload() {
        return mAppDatabase.cdsDao().getCdsListAndUpload();
    }


}
