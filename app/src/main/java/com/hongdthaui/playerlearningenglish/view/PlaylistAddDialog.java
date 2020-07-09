package com.hongdthaui.playerlearningenglish.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.hongdthaui.playerlearningenglish.R;

/**
 * Created by hongdthaui on 7/9/2020.
 */
public class PlaylistAddDialog extends Dialog {
    public interface OKListener{
        public void newPlaylistNameEnter(String playlistName);
    }
    private Context context;
    private PlaylistAddDialog.OKListener okListener;
    private Button btOk;
    private Button btCancel;
    private EditText etName;

    public PlaylistAddDialog(@NonNull Context context, PlaylistAddDialog.OKListener listener) {
        super(context);
        this.context = context;
        this.okListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_playlist);

        btOk = findViewById(R.id.dialog_add_playlist_bt_ok);
        btCancel = findViewById(R.id.dialog_add_playlist_bt_cancel);
        etName = findViewById(R.id.dialog_add_playlist_et_name);
        etName.requestFocus();
        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                if(name==null||name.contains("")){
                    Toast.makeText(context,"Please input name",Toast.LENGTH_LONG).show();
                    return;
                }
                dismiss();
                okListener.newPlaylistNameEnter(name);
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
