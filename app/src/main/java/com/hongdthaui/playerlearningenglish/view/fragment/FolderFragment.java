package com.hongdthaui.playerlearningenglish.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hongdthaui.playerlearningenglish.MainActivity;
import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.model.Folder;
import com.hongdthaui.playerlearningenglish.utils.ItemClickSupport;
import com.hongdthaui.playerlearningenglish.view.adapter.FolderAdapter;
import com.hongdthaui.playerlearningenglish.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 6/18/2020.
 */
public class FolderFragment extends Fragment {
    RecyclerView recyclerView;
    MainViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        return inflater.inflate(R.layout.fragment_folder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FolderAdapter folderAdapter = new FolderAdapter(new ArrayList<>());
        recyclerView = view.findViewById(R.id.fargment_folder_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(folderAdapter);
        recyclerView.setLayoutManager(layoutManager);

        viewModel.getListFolder().observe(getViewLifecycleOwner(), new Observer<List<Folder>>() {
            @Override
            public void onChanged(List<Folder> folders) {
                folderAdapter.setFolders(folders);
                folderAdapter.notifyDataSetChanged();
            }
        });
        final MainActivity activity = (MainActivity) getActivity();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

            }
        });

    }
}
