package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailguru extends AppCompatActivity {
    TextView idg, namag,nipg, telpg, keling, keling1, keling2, keling3, keling4, keling5, keling6;
    String idginfo, namaginfo,nipginfo, telpginfo, kelinginfo, sivguru, kelinginfo1, kelinginfo2, kelinginfo3, kelinginfo4, kelinginfo5, kelinginfo6;
    public static final String tampil = "https://monitoring11a.000webhostapp.com/PROJEK/uploadguru/";
    ImageView ivguru;
    Button btneditguru, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailguru);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        idg = findViewById(R.id.idg);
        namag = findViewById(R.id.namag);
        nipg = findViewById(R.id.nipg);
        telpg = findViewById(R.id.telpg);
        keling = findViewById(R.id.keling);
        keling1 = findViewById(R.id.keling1);
        keling2 = findViewById(R.id.keling2);
        keling3 = findViewById(R.id.keling3);
        keling4 = findViewById(R.id.keling4);
        keling5 = findViewById(R.id.keling5);
        keling6 = findViewById(R.id.keling6);
        ivguru = findViewById(R.id.ivguru);
        btneditguru = findViewById(R.id.button21);
        home = findViewById(R.id.button24);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        idginfo = getIntent().getStringExtra("idg");
        namaginfo = getIntent().getStringExtra("namag");
        nipginfo = getIntent().getStringExtra("nipg");
        telpginfo = getIntent().getStringExtra("telpg");
        kelinginfo = getIntent().getStringExtra("keling");
        kelinginfo1 = getIntent().getStringExtra("keling1");
        kelinginfo2 = getIntent().getStringExtra("keling2");
        kelinginfo3 = getIntent().getStringExtra("keling3");
        kelinginfo4 = getIntent().getStringExtra("keling4");
        kelinginfo5 = getIntent().getStringExtra("keling5");
        kelinginfo6 = getIntent().getStringExtra("keling6");
        sivguru = getIntent().getStringExtra("fotog");

        idg.setText(idginfo);
        namag.setText(namaginfo);
        nipg.setText(nipginfo);
        telpg.setText(telpginfo);
        keling.setText(kelinginfo);
        keling1.setText(kelinginfo1);
        keling2.setText(kelinginfo2);
        keling3.setText(kelinginfo3);
        keling4.setText(kelinginfo4);
        keling5.setText(kelinginfo5);
        keling6.setText(kelinginfo6);

        Glide.with(this)

                .load(tampil+sivguru)

                .override(300, 200)

                .into(ivguru);

        btneditguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editguru();
            }
        });

    }

    private void editguru(){
        Intent intent = new Intent(detailguru.this, editprofilguru.class);
        intent.putExtra("idg", idginfo);
        intent.putExtra("namag",namaginfo);
        intent.putExtra("nipg",nipginfo);
        intent.putExtra("telpg",telpginfo);
        intent.putExtra("keling",kelinginfo);
        intent.putExtra("keling1",kelinginfo1);
        intent.putExtra("keling2",kelinginfo2);
        intent.putExtra("keling3",kelinginfo3);
        intent.putExtra("keling4",kelinginfo4);
        intent.putExtra("keling5",kelinginfo5);
        intent.putExtra("keling6",kelinginfo6);
        intent.putExtra("fotog",sivguru);
        startActivity(intent);
    }

}