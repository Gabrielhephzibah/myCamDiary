package com.enyata.camdiary.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.enyata.camdiary.data.model.db.BdsDataCollections;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface BdsDao {
    @Query("SELECT * FROM bds_data ORDER BY id DESC ")
    Flowable<List<BdsDataCollections>> getAllBdsDta();

    @Insert
    Completable addBdsData(BdsDataCollections bdsData);

    @Update
    Completable updateBdsData(BdsDataCollections bdsData);

    @Delete
    Completable deleteBdsData(BdsDataCollections bdsData);



}
