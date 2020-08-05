package com.hongdthaui.playerlearningenglish.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.databinding.ActivityLibraryBinding;
import com.hongdthaui.playerlearningenglish.manager.SongManager;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.utils.ItemClickSupport;
import com.hongdthaui.playerlearningenglish.view.adapter.SongAdapter;
import com.hongdthaui.playerlearningenglish.view.adapter.SongLibraryAdapter;
import com.hongdthaui.playerlearningenglish.viewmodel.LibraryViewModel;
import com.hongdthaui.playerlearningenglish.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {
    private LibraryViewModel viewModel;
    private ActivityLibraryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_library);
        binding.setViewModel(viewModel);

        Intent intent = getIntent();
        int id = intent.getIntExtra("playlist_id",0);
        String name = intent.getStringExtra("playlist_name");
        String title = intent.getStringExtra("playlist_title");
        int num = intent.getIntExtra("playlist_num",0);

        Playlist playlist = new Playlist(id,name,title,num);

       // Log.e("MUSIC","playlistName="+playlistName);
        viewModel.setPlaylist(playlist);

        SongLibraryAdapter songAdapter = new SongLibraryAdapter(viewModel.getSongList());
        binding.libraryRv.setAdapter(songAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.libraryRv.setLayoutManager(layoutManager);
        binding.libraryBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.onClickAdd(songAdapter.getItemStateArray());
                finish();
            }
        });
    }
}
