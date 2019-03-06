package com.example.jaffe.circlepoject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by jaffe on 4/24/2018.
 */

@Dao
public interface ScoresDao {

    @Query("SELECT * FROM highscores WHERE level = :level ORDER BY score desc")
    List<scores> loadLevel(int level);

    @Query("SELECT * FROM highscores WHERE user = :name")
    List<scores> loadNames(String name);


    @Insert
    void insertAll(scores... scores);
}
