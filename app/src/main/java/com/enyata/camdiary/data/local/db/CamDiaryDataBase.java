package com.enyata.camdiary.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.enyata.camdiary.data.local.db.dao.BdsDao;
import com.enyata.camdiary.data.local.db.dao.CdsDao;
import com.enyata.camdiary.data.local.db.dao.MilkDao;
import com.enyata.camdiary.data.local.db.dao.PdsDao;
import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.Converters;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;

import retrofit2.Converter;

@Database(entities = {CdsDataCollection.class,PdsDataCollection.class, BdsDataCollections.class, MilkCollection.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class CamDiaryDataBase extends RoomDatabase {
    public abstract CdsDao cdsDao();
    public abstract PdsDao pdsDao();
    public abstract BdsDao bdsDao();
    public abstract MilkDao milkDao();


}
