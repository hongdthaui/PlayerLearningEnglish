package com.hongdthaui.playerlearningenglish.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.databinding.ItemAlbumBinding;
import com.hongdthaui.playerlearningenglish.databinding.ItemSongBinding;
import com.hongdthaui.playerlearningenglish.model.Album;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.viewmodel.AlbumItemViewModel;
import com.hongdthaui.playerlearningenglish.viewmodel.SongItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder> {
    private List<Album> albumList;
    private ViewGroup viewGroup;
    public AlbumAdapter(List<Album> albums){
        this.albumList = albums;
    }
    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAlbumBinding itemAlbumBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_album,parent,false);
        viewGroup = parent;
        return new AlbumHolder(itemAlbumBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        holder.itemAlbumBinding.setViewModel(new AlbumItemViewModel(albumList.get(position)));
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public class AlbumHolder extends RecyclerView.ViewHolder {

        public ItemAlbumBinding itemAlbumBinding;

        public AlbumHolder(@NonNull ItemAlbumBinding itemAlbumBinding) {
            super(itemAlbumBinding.getRoot());
            this.itemAlbumBinding = itemAlbumBinding;
        }

    }
}
