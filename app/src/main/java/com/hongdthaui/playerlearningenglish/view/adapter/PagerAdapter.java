package com.hongdthaui.playerlearningenglish.view.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.view.fragment.AlbumFragment;
import com.hongdthaui.playerlearningenglish.view.fragment.FolderFragment;
import com.hongdthaui.playerlearningenglish.view.fragment.PlaylistFragment;
import com.hongdthaui.playerlearningenglish.view.fragment.SongFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    public PagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SongFragment();
                break;
            case 1:
                fragment = new AlbumFragment();
                break;
            case 2:
                fragment = new PlaylistFragment();
                break;
            case 3:
                fragment = new FolderFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = context.getString(R.string.tab_1);
                break;
            case 1:
                title = context.getString(R.string.tab_2);
                break;
            case 2:
                title = context.getString(R.string.tab_3);
                break;
            case 3:
                title = context.getString(R.string.tab_4);
                break;
        }
        return title;
    }
}
