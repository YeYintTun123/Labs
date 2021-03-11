package com.example.lab3.ui.Country;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lab3.R;

public class CountryDetail extends AppCompatActivity {

    TextView CountryName, Captial, Region,Pop,Nur;
    String NameS,CaptialS,RegionS,NurS,PopS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);
        CountryName = findViewById(R.id.CountryNametextView);
        Captial = findViewById(R.id.CaptialtextView);
        Region = findViewById(R.id.regiontextView);

        getData();
        setData();


    }

    private void getData(){
        NameS = getIntent().getStringExtra("Name");
        CaptialS = getIntent().getStringExtra("Captial");
        RegionS = getIntent().getStringExtra("Region");


    }

    private void setData()
    {
        CountryName.setText(NameS);
        Captial.setText(CaptialS);
        Region.setText(RegionS);

    }
}