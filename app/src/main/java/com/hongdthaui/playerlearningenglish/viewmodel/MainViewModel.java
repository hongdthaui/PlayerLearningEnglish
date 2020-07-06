package com.hongdthaui.playerlearningenglish.viewmodel;

import android.app.Application;
import android.app.ListActivity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.hongdthaui.playerlearningenglish.MainActivity;
import com.hongdthaui.playerlearningenglish.model.Album;
import com.hongdthaui.playerlearningenglish.model.Folder;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.model.SongManager;

import java.util.List;

/**
 * Created by hongdthaui on 6/18/2020.
 */
public class MainViewModel extends AndroidViewModel {
    private Context context;
    private SongManager manager;
    private MutableLiveData<List<Song>> listSong;
    private MutableLiveData<List<Album>> listAlbum;
    private MutableLiveData<List<Playlist>> listPlaylist;
    private MutableLiveData<List<Folder>> listFolder;

    public ObservableBoolean isShowPlayer = new ObservableBoolean(false);
    public ObservableField<String> txtAlarm = new ObservableField<>();
    public MainViewModel(@NonNull Application application) {
        super(application);
        listSong = new MutableLiveData<>();
        listAlbum = new MutableLiveData<>();
        listPlaylist = new MutableLiveData<>();
        listFolder = new MutableLiveData<>();
        manager = new SongManager(application.getApplicationContext());
    }
    public void fetchData(){
        if(MainActivity.READ_EXTERNAL_OK) {
            Log.e("MUSIC","MainViewModel fetchData()");
            manager.fetchData();
            listSong.setValue(manager.getListSong());
            listFolder.setValue(manager.getListFolder());
            listAlbum.setValue(manager.getListAlbum());
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
}
