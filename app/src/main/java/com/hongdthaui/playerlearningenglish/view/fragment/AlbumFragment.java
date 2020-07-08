package com.hongdthaui.playerlearningenglish.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.MainActivity;
import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.model.Album;
import com.hongdthaui.playerlearningenglish.utils.ItemClickSupport;
import com.hongdthaui.playerlearningenglish.view.adapter.AlbumAdapter;
import com.hongdthaui.playerlearningenglish.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 6/18/2020.
 */
public class AlbumFragment extends Fragment {
    RecyclerView recyclerView;
    MainViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        return inflater.inflate(R.layout.fragment_album,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
 /*       List<Album> albums = new ArrayList<>();
        albums.add(new Album(new Song("abc","abc","abc","abc","abc","abc",1000)));
        if (MainActivity.READ_EXTERNAL_OK){
            //songManager = new SongManager(getActivity().getApplicationContext());
            //albums = songManager.getmListAlbum();
        }
        albums.add(new Album(new Song("abc","abc","abc","abc","abc","abc",1000)));
        albums.add(new Album(new Song("abc","abc","abc","abc","abc","abc",1000)));
        albums.add(new Album(new Song("abc","abc","abc","abc","abc","abc",1000)));
        albums.add(new Album(new Song("abc","abc","abc","abc","abc","abc",1000)));
        albums.add(new Album(new Song("abc","abc","abc","abc","abc","abc",1000)));
        albums.add(new Album(new Song("abc","abc","abc","abc","abc","abc",1000)));
        for (Album song:albums
             ) {
            Log.e("MUSIC","albums="+song.getName());
        }*/
        AlbumAdapter albumAdapter = new AlbumAdapter(new ArrayList<>());
        recyclerView = view.findViewById(R.id.fargment_album_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        //AutoFitGirdLayoutManager layoutManager = new AutoFitGirdLayoutManager(getActivity(),500);
        recyclerView.setAdapter(albumAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        viewModel.getListAlbum().observe(getViewLifecycleOwner(), new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                for (Album album:albums){
                    Log.e("MUSIC","Album=="+album.getName());
                }
                albumAdapter.setAlbumList(albums);
                albumAdapter.notifyDataSetChanged();
            }
        });
        final MainActivity activity = (MainActivity) getActivity();
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
               // activity.onPlay(position,songAdapter.getSongList());
            }
        });

    }
}
