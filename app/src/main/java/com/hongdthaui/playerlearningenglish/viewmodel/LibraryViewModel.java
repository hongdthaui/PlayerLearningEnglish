package com.hongdthaui.playerlearningenglish.viewmodel;

import android.app.Application;
import android.util.Log;
import android.util.SparseBooleanArray;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.hongdthaui.playerlearningenglish.manager.SongManager;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.room.PlaylistDatabase;

import java.util.List;

/**
 * Created by hongdthaui on 7/9/2020.
 */
public class LibraryViewModel extends AndroidViewModel {
    private List<Song> songList;
    private Playlist playlist;
    private PlaylistDatabase db;

    public LibraryViewModel(@NonNull Application application) {
        super(application);
        songList = SongManager.listSong;
        db = PlaylistDatabase.getInstance(application.getApplicationContext());
    }

    public void onClickAdd(SparseBooleanArray itemStateArray){
        if (itemStateArray.size()<=0){
            return;
        }

        int num = playlist.getNum();
        for (int i=0;i<itemStateArray.size();i++){
            Song song = songList.get(itemStateArray.keyAt(i));
            song.setPlaylist(playlist.getName());
            db.playlistSongsDao().insertSong(song);
            num++;

        }
        playlist.setNum(num);
        //Log.e("LibraryViewModel","playlist="+playlist.toString());
        db.playlistDao().updatePlaylist(playlist);
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public List<Song> getSongList() {
        return songList;
    }
}
