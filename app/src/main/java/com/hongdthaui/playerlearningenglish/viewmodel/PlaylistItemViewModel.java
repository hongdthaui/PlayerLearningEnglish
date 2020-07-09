package com.hongdthaui.playerlearningenglish.viewmodel;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.hongdthaui.playerlearningenglish.model.Playlist;

/**
 * Created by hongdthaui on 7/2/2020.
 */
public class PlaylistItemViewModel {
    public ObservableField<String> name;
    public ObservableInt num;
    public ObservableInt icon;
    public PlaylistItemViewModel(Playlist playlist) {
        name = new ObservableField<>(playlist.getTitle());
        num = new ObservableInt(playlist.getNum());
        icon = new ObservableInt(playlist.getIcon());
    }
}
