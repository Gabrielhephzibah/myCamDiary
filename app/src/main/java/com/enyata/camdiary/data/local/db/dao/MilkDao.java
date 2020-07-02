package com.enyata.camdiary.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
@Dao
public interface MilkDao {

    @Query("SELECT * FROM milk_collection ORDER BY id DESC")
    Flowable<List<MilkCollection>> getAllMilkCollection();

    @Insert
    Completable addMilkCollection(MilkCollection milkCollection);

    @Update
    Completable updateMilkCollection(MilkCollection milkCollection);

    @Delete
    Completable deleteMilkCollection(MilkCollection milkCollection);

}
