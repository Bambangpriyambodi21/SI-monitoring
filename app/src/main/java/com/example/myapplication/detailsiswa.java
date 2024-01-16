package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailsiswa extends AppCompatActivity {
    TextView ids, nissiswa, namas, kelas, telps, pembimbing, industri;
    public static final String tampil = "https://monitoring11a.000webhostapp.com/PROJEK/uploadsiswaa/";
    public static final String tampilprofil = "https://monitoring11a.000webhostapp.com/PROJEK/uploadsiswa/profile.png";
    String idsinfo, nissiswainfo, namasinfo, kelasinfo, telpsinfo, pembimbinginfo, industriinfo, sivsiswa;
    private ImageView ivsiswa;
    private Byte aByte;
    private Bitmap bitmap;
    Button btnedits, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsiswa);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        ids = findViewById(R.id.ids);
        nissiswa = findViewById(R.id.nissiswa);
        namas = findViewById(R.id.namas);
        kelas = findViewById(R.id.kelas);
        telps = findViewById(R.id.telps);
        pembimbing = findViewById(R.id.pembimbing);
        industri = findViewById(R.id.industri);
        ivsiswa = findViewById(R.id.ivsiswa);
        btnedits = findViewById(R.id.button19);
        home = findViewById(R.id.button24);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        idsinfo = getIntent().getStringExtra("ids");
        nissiswainfo = getIntent().getStringExtra("nis");
        namasinfo = getIntent().getStringExtra("namas");
        kelasinfo = getIntent().getStringExtra("kelas");
        telpsinfo = getIntent().getStringExtra("telps");
        pembimbinginfo = getIntent().getStringExtra("pembimbing");
        industriinfo = getIntent().getStringExtra("industri");
        sivsiswa = getIntent().getStringExtra("foto");

        ids.setText(idsinfo);
        nissiswa.setText(nissiswainfo);
        namas.setText(namasinfo);
        kelas.setText(kelasinfo);
        telps.setText(telpsinfo);
        pembimbing.setText(pembimbinginfo);
        industri.setText(industriinfo);

        Glide.with(this)

                .load(tampil+sivsiswa)

                .override(300, 200)

                .into(ivsiswa);

        btnedits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editsiswa();
            }
        });

    }

    private void editsiswa(){
        Intent intent = new Intent(detailsiswa.this, editprofilsiswa.class);
        intent.putExtra("ids", idsinfo);
        intent.putExtra("nis",nissiswainfo);
        intent.putExtra("namas",namasinfo);
        intent.putExtra("kelas",kelasinfo);
        intent.putExtra("telps",telpsinfo);
        intent.putExtra("pembimbing",pembimbinginfo);
        intent.putExtra("industri", industriinfo);
        intent.putExtra("foto", sivsiswa);
        startActivity(intent);
    }
}