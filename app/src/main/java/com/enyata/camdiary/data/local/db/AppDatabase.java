

package com.enyata.camdiary.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.enyata.camdiary.data.model.db.Option;
import com.enyata.camdiary.data.model.db.Question;
import com.enyata.camdiary.data.model.db.User;

/**
 * Created by Sanni Michael on 10/12/2019
 */

@Database(entities = {User.class, Question.class, Option.class}, version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

}
