package com.hongdthaui.playerlearningenglish.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.databinding.ItemFolderBinding;
import com.hongdthaui.playerlearningenglish.databinding.ItemPlaylistBinding;
import com.hongdthaui.playerlearningenglish.model.Folder;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.viewmodel.FolderItemViewModel;
import com.hongdthaui.playerlearningenglish.viewmodel.PlaylistItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 7/2/2020.
 */
public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderHolder>{
    private ViewGroup viewGroup;
    private List<Folder> folders;
    public FolderAdapter(List<Folder> folders) {
        this.folders = folders;
    }

    @NonNull
    @Override
    public FolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFolderBinding itemFolderBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_folder,parent,false);
        viewGroup = parent;
        return new FolderHolder(itemFolderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderHolder holder, int position) {
        holder.itemFolderBinding.setViewModel(new FolderItemViewModel(folders.get(position)));
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public class FolderHolder extends RecyclerView.ViewHolder{
        ItemFolderBinding itemFolderBinding;
        public FolderHolder(@NonNull ItemFolderBinding itemFolderBinding) {
            super(itemFolderBinding.getRoot());
            this.itemFolderBinding = itemFolderBinding;
        }
    }
}
