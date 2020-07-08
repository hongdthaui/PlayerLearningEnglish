package com.hongdthaui.playerlearningenglish.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hongdthaui.playerlearningenglish.model.Playlist;

import java.util.List;

/**
 * Created by hongdthaui on 7/6/2020.
 */
@Dao
public interface PlaylistDao {
    @Query("SELECT * FROM playlist")
    public List<PlaylistDB> getAllPlaylist();

    @Query("SELECT * FROM playlist WHERE name=:name")
    public PlaylistDB getPlaylist(String name);

    @Insert
    void insertPlaylist(PlaylistDB playlist);

    @Delete
    void deletePlaylist(PlaylistDB playlist);

    @Update
    void updatePlaylist(PlaylistDB playlist);
}
