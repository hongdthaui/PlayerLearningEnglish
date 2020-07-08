package com.hongdthaui.playerlearningenglish.model;


public class Song {
    private String name;
    private String title;
    private String album;
    private String artist;
    private String path;
    private int duration;

    public Song() {
    }
    public Song( String name, String title, String album, String artist, String path, int duration){

        this.name = name;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.path = path;
        this.duration = duration;
    }
    public Song(String title, String artist, String path, int duration){
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.duration = duration;
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
}
