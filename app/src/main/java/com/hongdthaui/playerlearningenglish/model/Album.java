package com.hongdthaui.playerlearningenglish.model;

import androidx.appcompat.app.AppCompatViewInflater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 6/27/2020.
 */
public class Album {
    private String name;
    private String artist;
    private List<Song> songs;

    public Album(Song song){
        this.name = song.getAlbum();
        this.artist = song.getArtist();
        songs = new ArrayList<>();
        songs.add(song);
    }
    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public int getNum() {
        return songs.size();
    }

    public List<Song> getSongs() {
        return songs;
    }
}
