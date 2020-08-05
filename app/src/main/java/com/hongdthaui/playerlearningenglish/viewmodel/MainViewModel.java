package com.hongdthaui.playerlearningenglish.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hongdthaui.playerlearningenglish.MainActivity;
import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.model.Album;
import com.hongdthaui.playerlearningenglish.model.Folder;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.manager.SongManager;
import com.hongdthaui.playerlearningenglish.room.PlaylistDatabase;
import com.hongdthaui.playerlearningenglish.view.activity.SongListActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.hongdthaui.playerlearningenglish.utils.Config.FAVORITES_NAME;
import static com.hongdthaui.playerlearningenglish.utils.Config.HISTORY_NAME;
import static com.hongdthaui.playerlearningenglish.utils.Config.LAST_ADDED_NAME;
import static com.hongdthaui.playerlearningenglish.utils.Config.MOST_PLAYED_NAME;

/**
 * Created by hongdthaui on 6/18/2020.
 */
public class MainViewModel extends AndroidViewModel {
    private Context context;
    private SongManager songManager;
    private MutableLiveData<List<Song>> listSong;
    private MutableLiveData<List<Album>> listAlbum;
    private MutableLiveData<List<Folder>> listFolder;
    private PlaylistDatabase db;

    public ObservableBoolean isShowPlayer = new ObservableBoolean(false);
    public ObservableField<String> txtAlarm = new ObservableField<>();
    public ObservableInt numMostPlaylist = new ObservableInt(0);
    public ObservableInt numFavoritesPlaylist = new ObservableInt(0);
    public ObservableInt numHistoryPlaylist = new ObservableInt(0);
    public ObservableInt numLastAddPlaylist = new ObservableInt(0);
    public MainViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        db = PlaylistDatabase.getInstance(context);

        listSong = new MutableLiveData<>();
        listAlbum = new MutableLiveData<>();
        listFolder = new MutableLiveData<>();
        songManager = new SongManager(context);
    }


    public void fetchData(){
        if(MainActivity.READ_EXTERNAL_OK) {
            songManager.fetchData();

            listSong.setValue(songManager.getListSong());
            listFolder.setValue(songManager.getListFolder());
            listAlbum.setValue(songManager.getListAlbum());

        }
    }
    public void onClickTop(String playlist){
        Intent intent = new Intent(context, SongListActivity.class);
        intent.putExtra("playlist_id",0);
        intent.putExtra("playlist_name",playlist);
        intent.putExtra("playlist_title",playlist);
        intent.putExtra("playlist_num",0);
        context.startActivity(intent);
    }
    public void onClickDown(){
        //Log.e("MUSC","clickkk");
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

    public LiveData<List<Playlist>> getListPlaylist() {
        return db.playlistDao().getAllPlaylist();
    }

    public LiveData<Integer> getNumHistory() {
        return db.playlistSongsDao().getCount(HISTORY_NAME);
    }

    public void setNumHistoryPlaylist(int num) {
        this.numHistoryPlaylist.set(num);
    }

    public LiveData<Integer> getNumMost() {
        return db.playlistSongsDao().getCount(MOST_PLAYED_NAME);
    }

    public void setNumMostPlaylist(int num) {
        this.numMostPlaylist.set(num);
    }

    public LiveData<Integer> getNumFavorites() {
        return db.playlistSongsDao().getCount(FAVORITES_NAME);
    }

    public void setNumFavoritesPlaylist(int num) {
        this.numFavoritesPlaylist.set(num);
    }

    public LiveData<Integer> getNumLastAdd() {
        return db.playlistSongsDao().getCount(LAST_ADDED_NAME);
    }

    public void setNumLastAddPlaylist(int num) {
        this.numLastAddPlaylist.set(num);
    }

    public MutableLiveData<List<Folder>> getListFolder() {
        return listFolder;
    }

    public void newPlaylistName(String playlistName) {
        db.playlistDao().insertPlaylist(new Playlist(playlistName.replace(" ", "").toLowerCase(), playlistName,0));
    }

    public void onPlay(int position, List<Song> songs) {

        updateMostPlayed(songs.get(position));//up most fisrt because  num

        updateHistory(songs.get(position));

    }

    private void updateMostPlayed(Song song) {
        List<Song> songDB = db.playlistSongsDao().getSong("mostPlayed",song.getName());
        if (songDB.size()>0){
            for (Song s:songDB) {
                s.setNumListen(s.getNumListen()+1);
                db.playlistSongsDao().updateSong(s);
            }
        }else {
            song.setPlaylist("mostPlayed");
            song.setNumListen(1);
            db.playlistSongsDao().insertSong(song);
        }
    }

    private void updateHistory(Song song) {
        song.setPlaylist("history");
        List<Song> songDB = db.playlistSongsDao().getSong("history",song.getName());
        //Log.e("MUSIC","song="+song.toString());
        for (Song s:songDB) {
           // Log.e("MUSIC","songDB="+s.toString());
            db.playlistSongsDao().deleteSong(s);
        }
        song.setNumListen(0);//for query getSongs oder by id
        db.playlistSongsDao().insertSong(song);
    }
}
