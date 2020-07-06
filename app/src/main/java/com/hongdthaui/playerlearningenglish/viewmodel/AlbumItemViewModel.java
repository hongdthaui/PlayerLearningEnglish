package com.hongdthaui.playerlearningenglish.viewmodel;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.hongdthaui.playerlearningenglish.model.Album;

/**
 * Created by hongdthaui on 6/27/2020.
 */
public class AlbumItemViewModel {
    public ObservableField<String> name;
    public ObservableField<String> artist;
    public ObservableInt num;
    public AlbumItemViewModel(Album album){
        name = new ObservableField<>(album.getName());
        artist = new ObservableField<>(album.getArtist());
        num = new ObservableInt(album.getNum());
    }
}
