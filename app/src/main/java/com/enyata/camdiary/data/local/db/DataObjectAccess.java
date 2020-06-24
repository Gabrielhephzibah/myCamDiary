package com.enyata.camdiary.data.local.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.enyata.camdiary.data.model.db.MilkCollection;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface DataObjectAccess {
    @Query("SELECT * FROM  MilkCollection ORDER BY id DESC ")
    Flowable<List<MilkCollection>>getAllMilk();
    @Insert
    Completable addMilkCollection(MilkCollection milkCollection);

    @Update
    Completable updateMilkCollection(MilkCollection milkCollection);

    @Delete
    Completable deleteMilkCollection(MilkCollection milkCollection);
}
