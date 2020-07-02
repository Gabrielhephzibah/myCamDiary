package com.enyata.camdiary.data.model.db;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;


import com.enyata.camdiary.data.model.api.myData.ChurnDetailsData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<ChurnDetailsData> fromString(String value){
        Type listType = new TypeToken<List<ChurnDetailsData>>(){}.getType();
        List<ChurnDetailsData> churnDetails = new Gson().fromJson(value,listType);
        return churnDetails;

    }

    @TypeConverter
    public static String listToString(List<ChurnDetailsData> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
