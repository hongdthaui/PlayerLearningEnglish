package com.hongdthaui.playerlearningenglish.viewmodel;

import androidx.databinding.ObservableField;

import com.hongdthaui.playerlearningenglish.model.Song;

/**
 * Created by hongdthaui on 6/19/2020.
 */
public class LibrarySongItemViewModel {
    public ObservableField<String> name;
    public ObservableField<String> artist;
    public ObservableField<String> time;
    public LibrarySongItemViewModel(Song song){
        this.name = new ObservableField<>(song.getTitle());
        this.artist = new ObservableField<>(song.getArtist());
        this.time = new ObservableField<>();
    }
}
