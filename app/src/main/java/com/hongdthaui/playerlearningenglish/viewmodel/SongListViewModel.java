package com.hongdthaui.playerlearningenglish.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.room.PlaylistDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 7/9/2020.
 */
public class SongListViewModel extends AndroidViewModel {
    public ObservableField<String> title;
    private Playlist playlist;
    private List<Song> songs;
    private PlaylistDatabase db;
    public ObservableBoolean isShowPlayer = new ObservableBoolean(false);
    public ObservableField<String> txtAlarm = new ObservableField<>();
    public ObservableBoolean isShowAdd = new ObservableBoolean(true);

    public SongListViewModel(@NonNull Application application) {
        super(application);
        title = new ObservableField<>();
        db = PlaylistDatabase.getInstance(application.getApplicationContext());
    }

    public void onClickBottomControl(){
        isShowPlayer.set(true);
    }
    public void onClickDown(){}
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
        this.title.set(playlist.getTitle());
    }

    public LiveData<List<Song>> getSongs(){
        return db.playlistSongsDao().getSongs(playlist.getName());
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
        if(songs.size()>0)
            isShowAdd.set(false);
        else
            isShowAdd.set(true);
    }
}
