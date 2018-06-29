package com.example.android.journalapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by tshepisomogapi on 2018/06/29.
 */

@Database(entities = {DiaryEntry.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {


    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "journalapp";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {

                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        // TODO (2) Use this temporarily
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return sInstance;
    }

    public abstract EntryDao entryDao();


}
