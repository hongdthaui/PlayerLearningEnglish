package com.hongdthaui.playerlearningenglish.view.fragment;

import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.MainActivity;
import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.view.activity.SongListActivity;
import com.hongdthaui.playerlearningenglish.databinding.FragmentPlaylistBinding;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.utils.ItemClickSupport;
import com.hongdthaui.playerlearningenglish.view.PlaylistAddDialog;
import com.hongdthaui.playerlearningenglish.view.adapter.Playlist2Adapter;
import com.hongdthaui.playerlearningenglish.view.adapter.PlaylistAdapter;
import com.hongdthaui.playerlearningenglish.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.hongdthaui.playerlearningenglish.utils.Config.FAVORITES_NAME;
import static com.hongdthaui.playerlearningenglish.utils.Config.HISTORY_NAME;
import static com.hongdthaui.playerlearningenglish.utils.Config.LAST_ADDED_NAME;
import static com.hongdthaui.playerlearningenglish.utils.Config.MOST_PLAYED_NAME;

/**
 * Created by hongdthaui on 6/18/2020.
 */
public class PlaylistFragment extends Fragment {
    RecyclerView recyclerView2;
    MainViewModel viewModel;
    FragmentPlaylistBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_playlist,container,false);
        binding.setViewModel(viewModel);
        registerLiveDataListenner();
        return binding.getRoot();
    }

    private void registerLiveDataListenner() {
        viewModel.getNumHistory().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                viewModel.setNumHistoryPlaylist(integer.intValue());
            }
        });
        viewModel.getNumFavorites().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                viewModel.setNumFavoritesPlaylist(integer.intValue());
            }
        });
        viewModel.getNumLastAdd().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                viewModel.setNumLastAddPlaylist(integer.intValue());
            }
        });
        viewModel.getNumMost().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                viewModel.setNumMostPlaylist(integer.intValue());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Playlist2Adapter playlist2Adapter = new Playlist2Adapter(new ArrayList<>());
        recyclerView2 = view.findViewById(R.id.fargment_playlist_rv2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());

        recyclerView2.setAdapter(playlist2Adapter);
        recyclerView2.setLayoutManager(layoutManager2);

        viewModel.getListPlaylist().observe(getViewLifecycleOwner(), new Observer<List<Playlist>>() {
            @Override
            public void onChanged(List<Playlist> playlists) {
                for (Playlist playlist:playlists)
                    Log.e("MUSIC","playlist="+playlist.toString());
                playlist2Adapter.setPlaylists(playlists);
                playlist2Adapter.notifyDataSetChanged();
            }
        });


        ItemClickSupport.addTo(recyclerView2).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Playlist playlist = playlist2Adapter.getPlaylists().get(position);
                Intent intent = new Intent(getActivity(), SongListActivity.class);
                intent.putExtra("playlist_id",playlist.getId());
                intent.putExtra("playlist_name",playlist.getName());
                intent.putExtra("playlist_title",playlist.getTitle());
                intent.putExtra("playlist_num",playlist.getNum());
                startActivity(intent);
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
        binding.fragmentPlaylistMostPlayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTop(MOST_PLAYED_NAME);
            }
        });
        binding.fragmentPlaylistFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTop(FAVORITES_NAME);
            }
        });
        binding.fragmentPlaylistHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTop(HISTORY_NAME);
            }
        });
        binding.fragmentPlaylistLastAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTop(LAST_ADDED_NAME);
            }
        });
    }
    public void onClickTop(String playlist){
        String name = "";
        if (playlist.equals(MOST_PLAYED_NAME)) name = getString(R.string.most_played);
        if (playlist.equals(FAVORITES_NAME)) name = getString(R.string.favorites);
        if (playlist.equals(HISTORY_NAME)) name = getString(R.string.histoty);
        if (playlist.equals(LAST_ADDED_NAME)) name = getString(R.string.last_added);

        Intent intent = new Intent(getActivity(), SongListActivity.class);
        intent.putExtra("playlist_id",0);
        intent.putExtra("playlist_name",playlist);
        intent.putExtra("playlist_title",name);
        intent.putExtra("playlist_num",0);
        startActivity(intent);
    }
}
