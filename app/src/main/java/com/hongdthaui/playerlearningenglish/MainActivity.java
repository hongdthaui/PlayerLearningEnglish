package com.hongdthaui.playerlearningenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.hongdthaui.playerlearningenglish.databinding.ActivityMainBinding;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.view.adapter.PagerAdapter;
import com.hongdthaui.playerlearningenglish.viewmodel.MainViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ActivityMainBinding mainBinding;

    private int countBackPressed = 0;
    private BottomSheetBehavior sheetBehavior;
    private ConstraintLayout clBottomSheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainBinding.setViewModel(viewModel);
        setStatusBarGradiant();
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), getApplicationContext());
        mainBinding.mainViewPager.setAdapter(adapter);
        mainBinding.activityMainTabLayout.setupWithViewPager(mainBinding.mainViewPager);

        clBottomSheet = findViewById(R.id.bottom_sheet_cl_player);
        sheetBehavior = BottomSheetBehavior.from(clBottomSheet);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int status) {
                if (status == BottomSheetBehavior.STATE_DRAGGING) {
                    //viewModel.isShowPlayer.set(true);
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

    }

    //setbg status bar = background
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarGradiant() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Drawable background = getResources().getDrawable(R.drawable.bg_main);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    @Override
    public void onBackPressed() {
        countBackPressed++;
        if (countBackPressed == 1) {
            if (sheetBehavior != null && sheetBehavior.getState() !=
                    BottomSheetBehavior.STATE_HIDDEN) {
                viewModel.isShowPlayer.set(false);
                countBackPressed = 0;
                return;
            }
        }
        if (countBackPressed == 1 || countBackPressed > 1) {
            if (sheetBehavior != null && sheetBehavior.getState() ==
                    BottomSheetBehavior.STATE_HIDDEN) {
                countBackPressed = 0;
                super.onBackPressed();
            }

        }

    }

    private ArrayList<HashMap<String, String>> getPlayList(String rootPath) {
        ArrayList<HashMap<String, String>> fileList = new ArrayList<>();


        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    HashMap<String, String> song = new HashMap<>();
                    song.put("file_path", file.getAbsolutePath());
                    song.put("file_name", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {
            return null;
        }
    }

    public void onPlay(int position, List<Song> songList) {
        //sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
       // viewModel.txtAlarm.set("23:59");
        viewModel.isShowPlayer.set(true);
    }

    @BindingAdapter("bottomSheetState")
    public static void bottomSheetState(ConstraintLayout layout, ObservableBoolean state) {
        Log.e("MUCIC", "bottomSheetState");
        BottomSheetBehavior behavior = BottomSheetBehavior.from(layout);
        behavior.setState(state.get() ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_HIDDEN);
    }
}
