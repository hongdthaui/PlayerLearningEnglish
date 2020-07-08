package com.hongdthaui.playerlearningenglish.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.hongdthaui.playerlearningenglish.model.Song;

/**
 * Created by hongdthaui on 7/8/2020.
 */
@Entity(tableName = "playlist_songs")
public class PlaylistSongsDB {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "playlist")
    private String playlist;
    @ColumnInfo(name = "songTitle")
    private String songTitle;
    @ColumnInfo(name = "songArtist")
    private String songArtist;
    @ColumnInfo(name = "songPath")
    private String songPath;
    @ColumnInfo(name = "songDuration")
    private int songDuration;
    public PlaylistSongsDB(){}
    public PlaylistSongsDB(String name, Song song){
        this.playlist = name;
        this.songTitle = song.getTitle();
        this.songArtist = song.getArtist();
        this.songPath = song.getPath();
        this.songDuration = song.getDuration();
    }

    public int getId() {
        return id;
    }

    public String getPlaylist() {
        return playlist;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public String getSongPath() {
        return songPath;
    }

    public int getSongDuration() {
        return songDuration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public void setSongDuration(int songDuration) {
        this.songDuration = songDuration;
    }
}
