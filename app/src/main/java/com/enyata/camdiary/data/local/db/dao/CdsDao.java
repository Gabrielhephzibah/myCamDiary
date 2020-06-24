package com.enyata.camdiary.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.enyata.camdiary.data.model.db.CdsDataCollection;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CdsDao {
    @Query("SELECT * FROM cds_data ORDER BY id DESC ")
    Flowable<List<CdsDataCollection>> getAllCdsDta();

    @Insert
    Completable addCdsData(CdsDataCollection cdsData);

    @Update
    Completable updateCdsData(CdsDataCollection cdsData);

    @Delete
    Completable deleteCdsData(CdsDataCollection cdsData);


}
