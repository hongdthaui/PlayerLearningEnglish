package com.hongdthaui.playerlearningenglish.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.databinding.ItemPlaylistBinding;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.viewmodel.PlaylistItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 7/2/2020.
 */
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder>{
    private ViewGroup viewGroup;
    private List<Playlist> playlists = new ArrayList<>();

    public PlaylistAdapter(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public PlaylistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlaylistBinding itemPlaylistBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_playlist,parent,false);
        viewGroup = parent;
        return new PlaylistHolder(itemPlaylistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistHolder holder, int position) {
        holder.itemPlaylistBinding.setViewModel(new PlaylistItemViewModel(playlists.get(position)));
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public class PlaylistHolder extends RecyclerView.ViewHolder{
        ItemPlaylistBinding itemPlaylistBinding;
        public PlaylistHolder(@NonNull ItemPlaylistBinding itemPlaylistBinding) {
            super(itemPlaylistBinding.getRoot());
            this.itemPlaylistBinding = itemPlaylistBinding;
        }
    }
}
