package com.hongdthaui.playerlearningenglish.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.databinding.ItemPlaylist2Binding;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.viewmodel.PlaylistItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 7/2/2020.
 */
public class Playlist2Adapter extends RecyclerView.Adapter<Playlist2Adapter.PlaylistHolder>{
    private ViewGroup viewGroup;
    private List<Playlist> playlists = new ArrayList<>();

    public Playlist2Adapter(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public PlaylistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlaylist2Binding itemPlaylist2Binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_playlist2,parent,false);
        viewGroup = parent;
        return new PlaylistHolder(itemPlaylist2Binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistHolder holder, int position) {
        holder.itemPlaylist2Binding.setViewModel(new PlaylistItemViewModel(playlists.get(position)));
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public class PlaylistHolder extends RecyclerView.ViewHolder{
        ItemPlaylist2Binding itemPlaylist2Binding;
        public PlaylistHolder(@NonNull ItemPlaylist2Binding itemPlaylist2Binding) {
            super(itemPlaylist2Binding.getRoot());
            this.itemPlaylist2Binding = itemPlaylist2Binding;
        }
    }
}
