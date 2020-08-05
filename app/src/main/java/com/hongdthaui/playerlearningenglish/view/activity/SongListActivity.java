package com.hongdthaui.playerlearningenglish.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.databinding.ActivitySongListBinding;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.view.adapter.SongAdapter;
import com.hongdthaui.playerlearningenglish.viewmodel.SongListViewModel;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SongListViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySongListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_song_list);
        viewModel = ViewModelProviders.of(this).get(SongListViewModel.class);
        binding.setViewModel(viewModel);
        setSupportActionBar(binding.toolbar);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        SongAdapter adapter = new SongAdapter(new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        binding.songListRv.setLayoutManager(layoutManager);
        binding.songListRv.setAdapter(adapter);

        Intent intent = getIntent();
        int id = intent.getIntExtra("playlist_id",0);
        String name = intent.getStringExtra("playlist_name");
        String title = intent.getStringExtra("playlist_title");
        int num = intent.getIntExtra("playlist_num",0);

        getSupportActionBar().setTitle(title);


        Playlist playlist = new Playlist(id,name,title,num);
        viewModel.setPlaylist(playlist);

        viewModel.getSongs().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                for(Song s: songs)
                    Log.e("MUSIC","song= "+ s.toString());

                adapter.setSongList(songs);
                adapter.notifyDataSetChanged();
                viewModel.setSongs(songs);
            }
        });
        //Log.e("MUSIC","playlist="+playlist.getTitle());
        binding.songListBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SongListActivity.this, LibraryActivity.class);
                intent1.putExtra("playlist_id",playlist.getId());
                intent1.putExtra("playlist_name",playlist.getName());
                intent1.putExtra("playlist_title",playlist.getTitle());
                intent1.putExtra("playlist_num",playlist.getNum());
                startActivity(intent1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_song_list,menu);
        return true;
    }
}
