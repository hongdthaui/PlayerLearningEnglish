package com.hongdthaui.playerlearningenglish.viewmodel;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.hongdthaui.playerlearningenglish.model.Folder;

/**
 * Created by hongdthaui on 7/6/2020.
 */
public class FolderItemViewModel {
    public ObservableField<String> name;
    public ObservableInt num;
    public ObservableField<String> path;
    public FolderItemViewModel(Folder folder){
        name = new ObservableField<>(folder.getName());
        num = new ObservableInt(folder.getNum());
        path = new ObservableField<>(folder.getPath());
    }
}
