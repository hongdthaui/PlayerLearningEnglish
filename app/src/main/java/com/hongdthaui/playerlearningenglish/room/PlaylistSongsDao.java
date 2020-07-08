package com.hongdthaui.playerlearningenglish.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hongdthaui.playerlearningenglish.model.Playlist;

import java.util.List;

/**
 * Created by hongdthaui on 7/8/2020.
 */
@Dao
public interface PlaylistSongsDao {
    @Query("SELECT * FROM playlist_songs WHERE playlist=:name")
    public List<PlaylistSongsDB> getSongs(String name);

    @Insert
    void insertSong(PlaylistSongsDB song);

    @Delete
    void deleteSong(PlaylistSongsDB song);

    @Update
    void updateSong(PlaylistSongsDB song);
}
