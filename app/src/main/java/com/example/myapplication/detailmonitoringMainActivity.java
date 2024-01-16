package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class detailmonitoringMainActivity<PNG> extends AppCompatActivity {
    public static final String tampil = "https://monitoring11a.000webhostapp.com/PROJEK/upload/";
    TextView idmtext, tglmtext, nistext, jamtext, kegiatantext, lokasitext, usertext;
    String idmtextA, tglmtextA, nistextA, jamtextA, kegiatantextA, lokasitextA, usertextA, stringImage;
    ImageView gambar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);

        idmtext = findViewById(R.id.idm2);
        tglmtext = findViewById(R.id.tglmm2);
        nistext = findViewById(R.id.nism2);
        jamtext = findViewById(R.id.jamm2);
        kegiatantext = findViewById(R.id.kegiatanm2);
        lokasitext = findViewById(R.id.lokasim2);
        usertext = findViewById(R.id.user2);
        gambar = findViewById(R.id.foto2);


        idmtextA = getIntent().getStringExtra("idm");
        tglmtextA = getIntent().getStringExtra("tglm");
        nistextA = getIntent().getStringExtra("nis");
        jamtextA = getIntent().getStringExtra("jam");
        kegiatantextA = getIntent().getStringExtra("kegiatan");
        lokasitextA = getIntent().getStringExtra("lokasi");
        usertextA = getIntent().getStringExtra("user");
        stringImage = getIntent().getStringExtra("foto");


        idmtext.setText(idmtextA);
        tglmtext.setText(tglmtextA);
        nistext.setText(nistextA);
        jamtext.setText(jamtextA);
        kegiatantext.setText(kegiatantextA);
        lokasitext.setText(lokasitextA);
        usertext.setText(usertextA);
//        fototext.set(fototextA);

        Glide.with(this)

                .load(tampil+stringImage)

                .override(300, 200)

                .into(gambar);
//        byte[] decodedString = Base64.decode(stringImage, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//
//        gambar.setImageBitmap(decodedByte);


    }

}

