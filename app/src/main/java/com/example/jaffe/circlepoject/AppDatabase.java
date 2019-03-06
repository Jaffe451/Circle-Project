package com.example.jaffe.circlepoject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by jaffe on 4/24/2018.
 */

@Database(entities = {scores.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract ScoresDao scoresDao();

    public static AppDatabase getDatabase(final Context context){
        if(instance == null) {
            synchronized (AppDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "highscores").build();
                }
            }
        }
        return instance;
    }
}
