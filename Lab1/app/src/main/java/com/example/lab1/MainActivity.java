package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String C_TAG = "On Create";
    private final String S_TAG = "On Start";
    private final String R_TAG = "On Resumed";
    private final String P_TAG = "On Paused";
    private final String ST_TAG = "On Stop";
    private final String D_TAG = "On_Destory";





    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);
        TextView TextView1 = (TextView) findViewById(R.id.TextView1);
        TextView1.setText("Current State = " + S_TAG );
        Log.i("Current State = ", S_TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        TextView TextView1 = (TextView) findViewById(R.id.TextView1);
        TextView1.setText("Current State = " + R_TAG );
        Log.i("Current State = ", R_TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setContentView(R.layout.activity_main);
        TextView TextView1 = (TextView) findViewById(R.id.TextView1);
        TextView1.setText("Current State = " + P_TAG);
        Log.i("Current State = ", P_TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        setContentView(R.layout.activity_main);
        TextView TextView1 = (TextView) findViewById(R.id.TextView1);
        TextView1.setText("Current State = " + ST_TAG);
        Log.i("Current State = ", ST_TAG);
    }


}