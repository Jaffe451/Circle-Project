package com.example.jaffe.circlepoject;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by jaffe on 4/24/2018.
 */

@Entity(tableName = "highscores")
public class scores {

    @PrimaryKey( autoGenerate = true )
    private int id;

    @ColumnInfo( name = "user")
    private String user;

    @ColumnInfo( name = "score")
    private int score;

    @ColumnInfo( name = "level")
    private int level;

    public scores(){

    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getUser(){
        return this.user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getLevel(){
        return this.level;
    }

    public void setLevel(int level){
        this.level = level;
    }

}
