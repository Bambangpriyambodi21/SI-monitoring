package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class detailpenempatanMainActivity extends AppCompatActivity {
    TextView idptext, kodeptext, kodeitext, namaptext, siswa1text, siswa2text, siswa3text, siswa4text, siswa5text, siswa6text;
    String idp, kodep, kodei, namap, siswa1,siswa2,siswa3,siswa4,siswa5,siswa6;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpenempatan_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        idptext = findViewById(R.id.idp);
        kodeptext = findViewById(R.id.kodep);
        kodeitext = findViewById(R.id.kodei);
        namaptext = findViewById(R.id.namap);
        siswa1text = findViewById(R.id.siswa1);
        siswa2text = findViewById(R.id.siswa2);
        siswa3text = findViewById(R.id.siswa3);
        siswa4text = findViewById(R.id.siswa4);
        siswa5text = findViewById(R.id.siswa5);
        siswa6text = findViewById(R.id.siswa6);
        home = findViewById(R.id.button24);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        idp = getIntent().getStringExtra("idp");
        kodep = getIntent().getStringExtra("kodep");
        kodei = getIntent().getStringExtra("kodei");
        namap = getIntent().getStringExtra("namapp");
        siswa1 = getIntent().getStringExtra("siswa1");
        siswa2 = getIntent().getStringExtra("siswa2");
        siswa3 = getIntent().getStringExtra("siswa3");
        siswa4 = getIntent().getStringExtra("siswa4");
        siswa5 = getIntent().getStringExtra("siswa5");
        siswa6 = getIntent().getStringExtra("siswa6");

        idptext.setText(idp);
        kodeptext.setText(kodep);
        kodeitext.setText(kodei);
        namaptext.setText(namap);
        siswa1text.setText(siswa1);
        siswa2text.setText(siswa2);
        siswa3text.setText(siswa3);
        siswa4text.setText(siswa4);
        siswa5text.setText(siswa5);
        siswa6text.setText(siswa6);

    }
}

