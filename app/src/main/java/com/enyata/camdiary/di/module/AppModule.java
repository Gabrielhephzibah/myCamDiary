

package com.enyata.camdiary.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.enyata.camdiary.BuildConfig;
import com.enyata.camdiary.R;
import com.enyata.camdiary.data.AppDataManager;
import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.local.db.AppDatabase;
import com.enyata.camdiary.data.local.db.AppDbHelper;
import com.enyata.camdiary.data.local.db.DbHelper;
import com.enyata.camdiary.data.local.prefs.AppPreferencesHelper;
import com.enyata.camdiary.data.local.prefs.PreferencesHelper;
import com.enyata.camdiary.data.remote.ApiHeader;
import com.enyata.camdiary.data.remote.ApiHelper;
import com.enyata.camdiary.data.remote.AppApiHelper;
import com.enyata.camdiary.di.ApiInfo;
import com.enyata.camdiary.di.DatabaseInfo;
import com.enyata.camdiary.di.PreferenceInfo;
import com.enyata.camdiary.utils.AppConstants;
import com.enyata.camdiary.utils.rx.AppSchedulerProvider;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Sanni Michael on 10/12/2019
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                preferencesHelper.getAccessToken());
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
