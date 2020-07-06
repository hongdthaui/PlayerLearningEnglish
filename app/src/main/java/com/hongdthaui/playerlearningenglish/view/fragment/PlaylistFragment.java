package com.hongdthaui.playerlearningenglish.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.MainActivity;
import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.model.Album;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.model.SongManager;
import com.hongdthaui.playerlearningenglish.utils.ItemClickSupport;
import com.hongdthaui.playerlearningenglish.view.adapter.Playlist2Adapter;
import com.hongdthaui.playerlearningenglish.view.adapter.PlaylistAdapter;
import com.hongdthaui.playerlearningenglish.view.adapter.SongAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 6/18/2020.
 */
public class PlaylistFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_playlist,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Playlist> playlists = new ArrayList<>();
        List<Playlist> playlists2 = new ArrayList<>();
        if (MainActivity.READ_EXTERNAL_OK){
           // songOnlines = SongManager.getListSongs(getActivity().getApplicationContext());
        }
        List<Song> songOnlines = new ArrayList<>();
        if (MainActivity.READ_EXTERNAL_OK){
           // SongManager songManager = new SongManager(getActivity().getApplicationContext());
           // songOnlines = songManager.getmListSongs();
        }

        playlists.add(new Playlist("Nghe nhiều nhất",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_stars_24dp));
        playlists.add(new Playlist("Nghe lần cuối",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_last_listener_24dp));
        playlists.add(new Playlist("Yêu thích",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_favorite_enable_24dp));
        playlists.add(new Playlist("Lịch sử",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_history_24dp));
        //playlists.add(new Playlist("Tuyển chọn nhạc dance",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_music_note_240dp));
        //playlists.add(new Playlist("Nhạc vàng",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_music_note_240dp));
        playlists2.add(new Playlist("Tuyển chọn nhạc dance",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_stars_24dp));
        playlists2.add(new Playlist("Nhạc vàng",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_last_listener_24dp));
        playlists2.add(new Playlist("Thiền tĩnh tâm",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_favorite_enable_24dp));
        playlists2.add(new Playlist("Nhạc trẻ chọn lọc",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_history_24dp));
        playlists2.add(new Playlist("Tuyển chọn nhạc dance",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_stars_24dp));
        playlists2.add(new Playlist("Nhạc vàng",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_last_listener_24dp));
        playlists2.add(new Playlist("Thiền tĩnh tâm",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_favorite_enable_24dp));
        playlists2.add(new Playlist("Nhạc trẻ chọn lọc",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_history_24dp));

        PlaylistAdapter playlistAdapter = new PlaylistAdapter(playlists);
        Playlist2Adapter songAdapter = new Playlist2Adapter(playlists2);

        recyclerView = view.findViewById(R.id.fargment_playlist_rv1);
        recyclerView2 = view.findViewById(R.id.fargment_playlist_rv2);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(playlistAdapter);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView2.setAdapter(songAdapter);
        recyclerView2.setLayoutManager(layoutManager2);

        final MainActivity activity = (MainActivity) getActivity();
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                /activity.onPlay(position,playlistAdapter.getSongList());
            }
        });

    }
}
