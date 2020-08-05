

package com.enyata.camdiary;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.cloudinary.android.MediaManager;
import com.enyata.camdiary.di.component.DaggerAppComponent;
import com.enyata.camdiary.utils.AppLogger;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Sanni Michael Tomiwa 3/12/2019.
 */

public class MvvmApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
//        super.onCreate();
        super.onCreate();
        try {
            Picasso.setSingletonInstance(new Picasso.Builder(this).build());
        } catch (IllegalStateException e) {
            Log.i("Exception", "Exception");
        }


        try {
            Map config = new HashMap();
            config.put("cloud_name", BuildConfig.CLOUDINARY_NAME);
            config.put("api_key", BuildConfig.CLOUDINARY_API_KEY);
            config.put("api_secret", BuildConfig.CLOUDINARY_API_SECRET);
            MediaManager.init(getApplicationContext().getApplicationContext(), config);
        } catch (IllegalStateException e) {
            Log.i("Exception", "Exception");
        }


        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        AppLogger.init();


        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }
}
