package com.hongdthaui.playerlearningenglish.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hongdthaui.playerlearningenglish.MainActivity;
import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.manager.PlaylistManager;
import com.hongdthaui.playerlearningenglish.model.Album;
import com.hongdthaui.playerlearningenglish.model.Folder;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.manager.SongManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 6/18/2020.
 */
public class MainViewModel extends AndroidViewModel {
    private Context context;
    private SongManager songManager;
    private PlaylistManager playlistManager;
    private MutableLiveData<List<Song>> listSong;
    private MutableLiveData<List<Album>> listAlbum;
    private MutableLiveData<List<Playlist>> listPlaylist;
    private MutableLiveData<List<Playlist>> topPlaylist;
    private MutableLiveData<List<Folder>> listFolder;

    public ObservableBoolean isShowPlayer = new ObservableBoolean(false);
    public ObservableField<String> txtAlarm = new ObservableField<>();
    public MainViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        listSong = new MutableLiveData<>();
        listAlbum = new MutableLiveData<>();
        listPlaylist = new MutableLiveData<>();
        listFolder = new MutableLiveData<>();
        topPlaylist = new MutableLiveData<>();
        songManager = new SongManager(context);
        playlistManager = new PlaylistManager(context);
    }
    public void fetchData(){
        if(MainActivity.READ_EXTERNAL_OK) {
            Log.e("MUSIC","MainViewModel fetchData()");
            songManager.fetchData();
            playlistManager.fetchData();

            listSong.setValue(songManager.getListSong());
            listFolder.setValue(songManager.getListFolder());
            listAlbum.setValue(songManager.getListAlbum());
            listPlaylist.setValue(playlistManager.getMyPlaylist());

            List<Playlist> top = new ArrayList<>();
            top.add(new Playlist(context.getString(R.string.most_played),playlistManager.getMostPlayed(),R.drawable.ic_stars_24dp));
            top.add(new Playlist(context.getString(R.string.favorites),playlistManager.getFavorites(),R.drawable.ic_favorite_enable_24dp));
            top.add(new  Playlist(context.getString(R.string.histoty),playlistManager.getHistory(),R.drawable.ic_history_24dp));
            top.add(new Playlist(context.getString(R.string.last_added),playlistManager.getLastAdded(),R.drawable.ic_last_listener_24dp));
            topPlaylist.setValue(top);
        }
    }
    public void onClickDown(){
        Log.e("MUSC","clickkk");
        isShowPlayer.set(false);
    }
    public void onClickBottomControl(){
            isShowPlayer.set(true);
    }

    public MutableLiveData<List<Song>> getListSong() {
        return listSong;
    }

    public MutableLiveData<List<Album>> getListAlbum() {
        return listAlbum;
    }

    public MutableLiveData<List<Playlist>> getListPlaylist() {
        return listPlaylist;
    }

    public MutableLiveData<List<Folder>> getListFolder() {
        return listFolder;
    }

    public MutableLiveData<List<Playlist>> getTopPlaylist() {
        return topPlaylist;
    }
}
