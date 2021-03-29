package com.example.lab4;


import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.example.lab4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final String TAG = "MAIN__";
    private MediaPlayer mediaPlayer;
    private static final String url = "http://stream.whus.org:8000/whusfm"; //";//http://vprbbc.streamguys.net:80/vprbbc24.mp3";
    private Button internetRadioButton;
    private Button onButton;
    private Button offButton;
    private boolean bRadioOn;

    private Spinner RadioStationSpinner;
    private ArrayList<String> radioStations = new ArrayList<String>();
    private String currentStation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioStations.add("http://14223.live.streamtheworld.com/SP_R3062003.mp3");
        radioStations.add("http://173.244.215.163:8340/;");
        radioStations.add("http://173.244.215.162:8210/;");
        radioStations.add("https://s7.myradiostream.com:59221/listen.mp3");
        radioStations.add("http://173.244.215.163:8420/;");

        RadioStationSpinner = findViewById(R.id.radioStationSelSpin);
        RadioStationSpinner.setOnItemSelectedListener(this);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,radioStations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RadioStationSpinner.setAdapter(adapter);





        mediaPlayer = new MediaPlayer();

        onButton = findViewById(R.id.onButton);
        offButton = findViewById(R.id.offButton);


        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bRadioOn == false) {
                    mediaPlayer = new MediaPlayer();
                    radioSetup(mediaPlayer, radioStations.get(0));
                    mediaPlayer.prepareAsync();
                    bRadioOn = true;
                }
            }
        });

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mediaPlayer.release();
                bRadioOn = false;
            }
        });
        
    }

    public void radioSetup(MediaPlayer mediaPlayer, String url) {

        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "onPrepared" );
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i(TAG, "onError: " + String.valueOf(what).toString());
                return false;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i(TAG, "onCompletion" );
                mediaPlayer.reset();
            }
        });

        try {
            mediaPlayer.setDataSource(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = null;
    }





    private void setUpMediaPlayer() {
        Handler handler = null;

        HandlerThread handlerThread = new HandlerThread("media player") {
            @Override
            public void onLooperPrepared() {
                Log.i(TAG, "onLooperPrepared");

            }
        };

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.radioStationSelSpin)
        {
            if (bRadioOn == true) {
                mediaPlayer.release();
                String selectedStation = parent.getItemAtPosition(position).toString();

                mediaPlayer = new MediaPlayer();
                radioSetup(mediaPlayer, selectedStation);
                mediaPlayer.prepareAsync();
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}