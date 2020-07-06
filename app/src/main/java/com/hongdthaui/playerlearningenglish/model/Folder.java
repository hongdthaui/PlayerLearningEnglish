package com.hongdthaui.playerlearningenglish.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 7/5/2020.
 */
public class Folder {
    private String name;
    private String path;
    private List<Song> songs;
    public Folder(String name, String path,Song song){
        this.name = name;
        this.path = path;
        this.songs = new ArrayList<>();
        this.songs.add(song);
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public String getPath() {
        return path;
    }

    public int getNum(){
        return this.songs.size();
    }
}
