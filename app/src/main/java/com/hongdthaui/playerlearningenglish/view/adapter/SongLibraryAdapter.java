package com.hongdthaui.playerlearningenglish.view.adapter;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.databinding.ItemSongBinding;
import com.hongdthaui.playerlearningenglish.databinding.ItemSongLibraryBinding;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.viewmodel.SongItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class SongLibraryAdapter extends RecyclerView.Adapter<SongLibraryAdapter.SongHolder> {
    private List<Song> songList;
    private ViewGroup viewGroup;
    private SparseBooleanArray itemStateArray = new SparseBooleanArray();
    public SongLibraryAdapter(List<Song> songs){
        this.songList = songs;
    }
    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongLibraryBinding itemSongBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_song_library,parent,false);
        viewGroup = parent;
        return new SongHolder(itemSongBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        holder.itemSongBinding.setViewModel(new SongItemViewModel(songList.get(position)));
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                if (!itemStateArray.get(position,false)){
                    holder.itemSongBinding.itemSongLibraryCheckBox.setChecked(true);
                    itemStateArray.put(position,true);
                }
                else {
                    holder.itemSongBinding.itemSongLibraryCheckBox.setChecked(false);
                    //itemStateArray.put(position,false);
                    itemStateArray.delete(position);
                }
            }
        });
    }

    public SparseBooleanArray getItemStateArray() {
        return itemStateArray;
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

    public class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ItemSongLibraryBinding itemSongBinding;
        private ItemClickListener itemClickListener;

        public SongHolder(@NonNull ItemSongLibraryBinding itemSongBinding) {
            super(itemSongBinding.getRoot());
            this.itemSongBinding = itemSongBinding;
            itemSongBinding.getRoot().setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onClick(View v, int position);
    }
}
