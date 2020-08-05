package com.hongdthaui.playerlearningenglish.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by hongdthaui on 7/8/2020.
 */
@Dao
public interface PlaylistSongsDao {
    @Query("SELECT * FROM playlist_songs WHERE playlist=:name ORDER BY numListen DESC, id DESC")
    LiveData<List<Song>> getSongs(String name);

    @Query("SELECT * FROM playlist_songs WHERE playlist=:playlistName AND name=:songName")
    List<Song> getSong(String playlistName, String songName);

    @Query("SELECT * FROM playlist_songs")
    LiveData<List<Song>> getAllSongs();

    @Insert
    void insertSong(Song songs);

    @Delete
    void deleteSong(Song song);

    @Update
    void updateSong(Song song);

    @Query("SELECT COUNT(*) FROM playlist_songs WHERE playlist=:name")
    LiveData<Integer> getCount(String name);
}
