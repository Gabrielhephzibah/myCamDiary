package com.enyata.camdiary.data.local.db;

import android.content.Context;

import androidx.room.Room;

public class DataBaseClient {
    private Context context;

    private static DataBaseClient myInstance;

    private CamDiaryDataBase camDiaryDataBase;

    public DataBaseClient(Context context){
        this.context = context;

        camDiaryDataBase = Room.databaseBuilder(context,CamDiaryDataBase.class,"CamDiary_app").build();
    }

    public static synchronized DataBaseClient getInstance(Context context){
        if (myInstance == null){
            myInstance = new DataBaseClient(context);
        }
        return myInstance;
    }

    public CamDiaryDataBase getNoteDataBase(){
        return camDiaryDataBase;
    }
}
