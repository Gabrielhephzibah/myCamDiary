package com.enyata.camdiary.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface PdsDao {
    @Query("SELECT * FROM pds_data ORDER BY id DESC ")
    Flowable<List<PdsDataCollection>>getAllPdsData();

    @Insert
    Completable addPdsData(PdsDataCollection pdsData);

    @Update
    Completable updatePdsData(PdsDataCollection pdsData);

    @Delete
    Completable deletePdsData(PdsDataCollection pdsData);

}
