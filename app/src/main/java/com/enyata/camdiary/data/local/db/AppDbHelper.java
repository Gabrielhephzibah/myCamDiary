

package com.enyata.camdiary.data.local.db;


import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by Sanni Michael on 10/12/2019
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

}
