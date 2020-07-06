package com.hongdthaui.playerlearningenglish.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private int icon;
    private List<Song> songs;
    public Playlist(String name, Song song, int icon){
        this.name = name;
        this.icon = icon;
        songs = new ArrayList<>();
        songs.add(song);
    }

    public String getName() {
        return name;
    }

    public int getNum(){
        return songs.size();
    }

    public int getIcon() {
        return icon;
    }
}
