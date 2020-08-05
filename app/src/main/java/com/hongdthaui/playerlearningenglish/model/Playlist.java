package com.hongdthaui.playerlearningenglish.model;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.hongdthaui.playerlearningenglish.model.Song;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "playlist")
public class Playlist {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private int num;
    public Playlist(){

    }
    @Ignore
    public Playlist(String name, String title, int num){
        this.name = name;
        this.title = title;
        this.num = num;
    }
    @Ignore
    public Playlist(int id, String name, String title, int num){
        this.id = id;
        this.name = name;
        this.title = title;
        this.num = num;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum(){
        return num;
    }

    @NonNull
    @Override
    public String toString() {
        return "id="+id+"name="+name+" num="+num;
    }
}
