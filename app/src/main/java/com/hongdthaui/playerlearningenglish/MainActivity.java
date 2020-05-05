package com.hongdthaui.playerlearningenglish;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private TextView textMaxTime;
    private TextView textCurrentPosition;
    private Button buttonPause;
    private Button buttonStart;
    private Button buttonPre;
    private Button buttonNext;
    private Button buttonSelect;
    private SeekBar seekBar;
    private Handler threadHandler = new Handler();
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.textCurrentPosition = (TextView) this.findViewById(R.id.txt_current);
        this.textMaxTime = (TextView) this.findViewById(R.id.txt_max_time);

        this.buttonSelect = (Button) this.findViewById(R.id.bt_select_song);

        this.buttonStart = (Button) this.findViewById(R.id.bt_start);
        this.buttonPause = (Button) this.findViewById(R.id.bt_pause);
        this.buttonPre = (Button) this.findViewById(R.id.bt_pre);
        this.buttonNext = (Button) this.findViewById(R.id.bt_next);

        this.buttonStart.setEnabled(false);
        this.buttonPause.setEnabled(false);
        this.buttonPre.setEnabled(false);
        this.buttonNext.setEnabled(false);


        this.seekBar = (SeekBar) this.findViewById(R.id.seekBar);
        this.seekBar.setClickable(false);

        this.buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Select new media source.
                selectMediaResource();
            }
        });

        this.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });
        this.buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPause();
            }
        });
        this.buttonPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRewind();
            }
        });
        this.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doFastForward();
            }
        });
        //MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.ghencovy);
       // mediaPlayer.start();

        // Create MediaPlayer.
        this.mediaPlayer = new MediaPlayer();
        mediaPlayer = new MediaPlayer();
        this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                doStop();
            }
        });
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                doComplete();
            }
        });
    }

    private void doComplete() {
        buttonStart.setEnabled(true);
        buttonPause.setEnabled(false);
        buttonPre.setEnabled(true);
        buttonNext.setEnabled(false);
    }

    private void doStop() {
        if(this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.stop();
        }
        buttonStart.setEnabled(true);
        buttonPause.setEnabled(false);
        buttonPre.setEnabled(false);
        buttonNext.setEnabled(false);
    }


    private void doFastForward() {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        // 5 seconds.
        int ADD_TIME = 5000;

        if(currentPosition + ADD_TIME < duration)  {
            this.mediaPlayer.seekTo(currentPosition + ADD_TIME);
        }
    }

    private void doRewind() {
        int currentPosition = this.mediaPlayer.getCurrentPosition();
        int duration = this.mediaPlayer.getDuration();
        // 5 seconds.
        int SUBTRACT_TIME = 5000;

        if(currentPosition - SUBTRACT_TIME > 0 )  {
            this.mediaPlayer.seekTo(currentPosition - SUBTRACT_TIME);
        }
        this.buttonNext.setEnabled(true);
    }

    private void doPause() {
        this.mediaPlayer.pause();
        this.buttonPause.setEnabled(false);
        this.buttonStart.setEnabled(true);
    }

    private void doStart() {
        if(this.mediaPlayer.isPlaying()) {
            return;
        }
        // The duration in milliseconds
        int duration = this.mediaPlayer.getDuration();

        int currentPosition = this.mediaPlayer.getCurrentPosition();
        if(currentPosition== 0)  {
            this.seekBar.setMax(duration);
            String maxTimeString = this.millisecondsToString(duration);
            this.textMaxTime.setText(maxTimeString);
        } else if(currentPosition== duration)  {
            // Resets the MediaPlayer to its uninitialized state.
            this.mediaPlayer.reset();
        }
        this.mediaPlayer.start();
        // Create a thread to update position of SeekBar.
        UpdateSeekBarThread updateSeekBarThread= new UpdateSeekBarThread();
        threadHandler.postDelayed(updateSeekBarThread,50);

        this.buttonPause.setEnabled(true);
        this.buttonStart.setEnabled(false);
        this.buttonPre.setEnabled(true);
        this.buttonNext.setEnabled(true);
    }

    private String millisecondsToString(int milliseconds) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) milliseconds);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds((long) milliseconds) ;
        return minutes + ":"+ seconds;
    }

    private void selectMediaResource() {
        this.selectRawMediaSource();
    }

    private void selectRawMediaSource() {
        String resName = MediaPlayerUtils.RAW_MEDIA_SAMPLE;
        MediaPlayerUtils.playRawMedia(this, this.mediaPlayer, resName);
    }
// Thread to Update position for SeekBar.
class UpdateSeekBarThread implements Runnable {

    public void run()  {
        int currentPosition = mediaPlayer.getCurrentPosition();
        String currentPositionStr = millisecondsToString(currentPosition);
        textCurrentPosition.setText(currentPositionStr);

        seekBar.setProgress(currentPosition);
        // Delay thread 50 milisecond.
        threadHandler.postDelayed(this, 50);
    }
}
}
