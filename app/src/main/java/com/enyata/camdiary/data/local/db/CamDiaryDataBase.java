package com.enyata.camdiary.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.enyata.camdiary.data.local.db.dao.BdsDao;
import com.enyata.camdiary.data.local.db.dao.CdsDao;
import com.enyata.camdiary.data.local.db.dao.PdsDao;
import com.enyata.camdiary.data.model.db.BdsDataCollections;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;

@Database(entities = {CdsDataCollection.class,PdsDataCollection.class, BdsDataCollections.class}, version = 1)
public abstract class CamDiaryDataBase extends RoomDatabase {
    public abstract CdsDao cdsDao();
    public abstract PdsDao pdsDao();
    public abstract BdsDao bdsDao();


}
