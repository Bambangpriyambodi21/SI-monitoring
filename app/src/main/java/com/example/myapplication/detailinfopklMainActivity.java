package com.example.myapplication;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class detailinfopklMainActivity extends AppCompatActivity {
    TextView idinfotext, ketinfotext, detinfotext;
    String idinfo, ketinfo, detinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infopkl_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        idinfotext = findViewById(R.id.idinfol);
        ketinfotext = findViewById(R.id.ketinfol);
        detinfotext = findViewById(R.id.detinfol);

        idinfo = getIntent().getStringExtra("idinfo");
        ketinfo = getIntent().getStringExtra("ketinfo");
        detinfo = getIntent().getStringExtra("detinfo");

        idinfotext.setText(idinfo);
        ketinfotext.setText(ketinfo);
        detinfotext.setText(detinfo);

    }
}
