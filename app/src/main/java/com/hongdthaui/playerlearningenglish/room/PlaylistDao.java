package com.hongdthaui.playerlearningenglish.room;

import android.icu.text.Replaceable;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hongdthaui.playerlearningenglish.model.Playlist;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

import static androidx.room.OnConflictStrategy.REPLACE;

/**
 * Created by hongdthaui on 7/6/2020.
 */
@Dao
public interface PlaylistDao {
    @Query("SELECT * FROM playlist")
    LiveData<List<Playlist>> getAllPlaylist();

    @Query("SELECT * FROM playlist WHERE name=:name")
    public Playlist getPlaylist(String name);

    @Insert(onConflict = REPLACE)
    void insertPlaylist(Playlist playlist);

    @Delete
    void deletePlaylist(Playlist playlist);

    @Update(onConflict = REPLACE)
    void updatePlaylist(Playlist playlist);
}
