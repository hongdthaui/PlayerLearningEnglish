package com.hongdthaui.playerlearningenglish.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import static com.hongdthaui.playerlearningenglish.room.InitSQL.VERSION_SQL;


/**
 * Created by hongdthaui on 7/6/2020.
 */
@Database(entities = {Playlist.class, Song.class}, version = VERSION_SQL)
public abstract class PlaylistDatabase extends RoomDatabase {
    private static PlaylistDatabase playlistDatabase;

    public abstract PlaylistDao playlistDao();

    public abstract PlaylistSongsDao playlistSongsDao();

    public static final String DB_NAME = "music_db";

    public static PlaylistDatabase getInstance(Context context) {
        if (playlistDatabase == null) {
            playlistDatabase = Room.databaseBuilder(context.getApplicationContext(), PlaylistDatabase.class, DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();

        }
        return playlistDatabase;
    }

    public static void destroyInstance() {
        playlistDatabase = null;
    }
}
