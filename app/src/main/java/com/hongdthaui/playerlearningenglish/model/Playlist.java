package com.hongdthaui.playerlearningenglish.model;


import com.hongdthaui.playerlearningenglish.model.Song;

import java.util.ArrayList;
import java.util.List;


public class Playlist {
    private String name;
    private String title;
    private int icon;
    private List<Song> songs;

    public Playlist(String title, List<Song> songs, int icon){
        this.title = title;
        this.songs = songs;
        this.icon = icon;
    }
    public Playlist(String name, String title, int icon){
        this.name = name;
        this.title = title;
        songs = new ArrayList<>();
        this.icon = icon;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getNum(){
        return songs.size();
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
