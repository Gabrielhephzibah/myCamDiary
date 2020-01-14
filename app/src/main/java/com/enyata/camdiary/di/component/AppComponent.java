

package com.enyata.camdiary.di.component;

import android.app.Application;

import com.enyata.camdiary.MvvmApp;
import com.enyata.camdiary.di.builder.ActivityBuilder;
import com.enyata.camdiary.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Sanni Michael on 10/12/2019
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(MvvmApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
