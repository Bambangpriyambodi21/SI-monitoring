package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailadmin extends AppCompatActivity {
    TextView ida, usera, namaa, telpa;
    String idsinfo, nissiswainfo, namasinfo, telpsinfo, pembimbinginfo, sivadmin;
    public static final String tampil = "https://monitoring11a.000webhostapp.com/PROJEK/uploadadmin/";
    ImageView ivadmin;
    Button btneditadmin, home;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailadmin);

//        actionBar = getSupportActionBar();
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        ida = findViewById(R.id.ida);
        usera = findViewById(R.id.tvusera);
        namaa = findViewById(R.id.tvnamaa);
        telpa = findViewById(R.id.tvtelpa);
        ivadmin = findViewById(R.id.ivadmin);
        btneditadmin = findViewById(R.id.button23);
        home = findViewById(R.id.button24);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        idsinfo = getIntent().getStringExtra("ida");
        nissiswainfo = getIntent().getStringExtra("usera");
        namasinfo = getIntent().getStringExtra("namaa");
        telpsinfo = getIntent().getStringExtra("telpa");
        pembimbinginfo = getIntent().getStringExtra("fotoa");

        ida.setText(idsinfo);
        usera.setText(nissiswainfo);
        namaa.setText(namasinfo);
        telpa.setText(telpsinfo);

        Glide.with(this)

                .load(tampil+pembimbinginfo)

                .override(300, 200)

                .into(ivadmin);

        btneditadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editadmin();
            }
        });

    }

//    @Override
//    public boolean onSupportNavigateUp()
//        onBackPressed();
//        return true;
//    }
//    @Override
//    public void onBackPressed(){
//        super.onBackPressed();
//    }

    private void editadmin(){
        Intent intent = new Intent(detailadmin.this, editprofiladmin.class);
        intent.putExtra("ida", idsinfo);
        intent.putExtra("usera", nissiswainfo);
        intent.putExtra("namaa",namasinfo);
        intent.putExtra("telpa",telpsinfo);
        intent.putExtra("fotoa",pembimbinginfo);
        startActivity(intent);
    }
}