package com.hongdthaui.playerlearningenglish.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * Created by hongdthaui on 6/18/2020.
 */
public class MainViewModel extends AndroidViewModel {
    public ObservableBoolean isShowPlayer = new ObservableBoolean(false);
    public ObservableField<String> txtAlarm = new ObservableField<>();
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
public void onClickDown(){
    Log.e("MUSC","clickkk");
    isShowPlayer.set(false);
}
public void onClickBottomControl(){
        isShowPlayer.set(true);
}
}
