package com.hongdthaui.playerlearningenglish.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.databinding.ItemSongBinding;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.viewmodel.SongItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> {
    private List<Song> songList;
    private ViewGroup viewGroup;
    public SongAdapter(List<Song> songs){
        this.songList = songs;
    }
    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongBinding itemSongBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_song,parent,false);
        viewGroup = parent;
        return new SongHolder(itemSongBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        holder.itemSongBinding.setViewModel(new SongItemViewModel(songList.get(position)));
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public class SongHolder extends RecyclerView.ViewHolder {

        public ItemSongBinding itemSongBinding;

        public SongHolder(@NonNull ItemSongBinding itemSongBinding) {
            super(itemSongBinding.getRoot());
            this.itemSongBinding = itemSongBinding;
        }

    }
}
