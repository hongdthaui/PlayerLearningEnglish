package com.hongdthaui.playerlearningenglish.room;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.hongdthaui.playerlearningenglish.model.Playlist;

/**
 * Created by hongdthaui on 8/1/2020.
 */
class InitSQL {
    public static final int VERSION_SQL = 7;
    public static void init(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        int version = preferences.getInt("VERSION", 0);
        if (version<VERSION_SQL){
            PlaylistDatabase database = PlaylistDatabase.getInstance(context);
            //top.add(new Playlist("mostPlayed",context.getString(R.string.most_played),playlistManager.getMostPlayed(),R.drawable.ic_stars_24dp));
            //top.add(new Playlist("favorites",context.getString(R.string.favorites),playlistManager.getFavorites(),R.drawable.ic_favorite_enable_24dp));
            //top.add(new  Playlist("history",context.getString(R.string.histoty),playlistManager.getHistory(),R.drawable.ic_history_24dp));
            //top.add(new Playlist("lastAdded",context.getString(R.string.last_added),playlistManager.getLastAdded(),R.drawable.ic_last_listener_24dp));

            //database.playlistDao().insertPlaylist(new Playlist("mostPlayed", "mostPlayed", default_icon,0));
            editor.putInt("VERSION",VERSION_SQL);
            editor.commit();
        }

    }
}
