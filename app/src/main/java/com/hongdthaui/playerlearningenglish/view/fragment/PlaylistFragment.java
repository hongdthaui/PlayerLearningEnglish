package com.hongdthaui.playerlearningenglish.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.MainActivity;
import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.databinding.FragmentPlaylistBinding;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.utils.ItemClickSupport;
import com.hongdthaui.playerlearningenglish.view.PlaylistAddDialog;
import com.hongdthaui.playerlearningenglish.view.adapter.Playlist2Adapter;
import com.hongdthaui.playerlearningenglish.view.adapter.PlaylistAdapter;
import com.hongdthaui.playerlearningenglish.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 6/18/2020.
 */
public class PlaylistFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    MainViewModel viewModel;
    FragmentPlaylistBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_playlist,container,false);
        binding.setViewModel(viewModel);
        return binding.getRoot();//inflater.inflate(R.layout.fragment_playlist,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //playlists.add(new Playlist("Tuyển chọn nhạc dance",new Song("abc","abc","abc","abc","abc","abc",1000),R.drawable.ic_music_note_240dp));


        PlaylistAdapter playlistAdapter = new PlaylistAdapter(new ArrayList<>());
        Playlist2Adapter playlist2Adapter = new Playlist2Adapter(new ArrayList<>());

        recyclerView = view.findViewById(R.id.fargment_playlist_rv1);
        recyclerView2 = view.findViewById(R.id.fargment_playlist_rv2);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(playlistAdapter);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView2.setAdapter(playlist2Adapter);
        recyclerView2.setLayoutManager(layoutManager2);
        viewModel.getTopPlaylist().observe(getViewLifecycleOwner(), new Observer<List<Playlist>>() {
            @Override
            public void onChanged(List<Playlist> playlists) {
                playlists.get(0).setName(getString(R.string.most_played));
                playlists.get(1).setName(getString(R.string.favorites));
                playlists.get(2).setName(getString(R.string.histoty));
                playlists.get(3).setName(getString(R.string.last_added));
                playlistAdapter.setPlaylists(playlists);
                playlistAdapter.notifyDataSetChanged();
            }
        });
        viewModel.getListPlaylist().observe(getViewLifecycleOwner(), new Observer<List<Playlist>>() {
            @Override
            public void onChanged(List<Playlist> playlists) {
                playlist2Adapter.setPlaylists(playlists);
                playlist2Adapter.notifyDataSetChanged();
            }
        });
        final MainActivity activity = (MainActivity) getActivity();
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                /activity.onPlay(position,playlistAdapter.getSongList());
            }
        });
        binding.fragmentPlaylistIbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaylistAddDialog.OKListener listener = new PlaylistAddDialog.OKListener() {
                    @Override
                    public void newPlaylistNameEnter(String playlistName) {
                        viewModel.newPlaylistName(playlistName);
                    }
                };
                final PlaylistAddDialog dialog = new PlaylistAddDialog(getActivity(),listener);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                dialog.show();
            }
        });


    }
}
