package com.hongdthaui.playerlearningenglish.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "playlist_songs")
public class Song {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;
    @ColumnInfo
    private String playlist;

    @ColumnInfo
    private String name;
    @ColumnInfo
    private String title;

    private String album;
    private String artist;
    @ColumnInfo
    private String path;
    @ColumnInfo
    private int duration;
    @ColumnInfo
    private int numListen;

    public Song() {
    }

    @Ignore
    public Song( String name, String title, String album, String artist, String path, int duration){

        this.name = name;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.path = path;
        this.duration = duration;
    }
    @Ignore
    public Song(String title, String artist, String path, int duration){
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.duration = duration;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getPath() {
        return path;
    }

    public int getDuration() {
        return duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumListen() {
        return numListen;
    }

    public void setNumListen(int numListen) {
        this.numListen = numListen;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", playlist='" + playlist + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", path='" + path + '\'' +
                ", duration=" + duration +
                ", numListen=" + numListen +
                '}';
    }
}
