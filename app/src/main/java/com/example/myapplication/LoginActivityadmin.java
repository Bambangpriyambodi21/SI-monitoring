package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.apiadmin.Apiclientadmin;
import com.example.myapplication.apiadmin.Apiinterfaceadmin;
import com.example.myapplication.modeladmin.loginadmin.Logindataadmin;
import com.example.myapplication.modeladmin.loginadmin.Loginadmin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityadmin extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername1, etPassword1;
    Button btnLogin1;
    String Username1, Password1;
    TextView tvRegister;
    Apiinterfaceadmin apiinterfaceadmin;
    SessionManageradmin sessionManageradmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivityadmin);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        etUsername1 = findViewById(R.id.etUsername1);
        etPassword1 = findViewById(R.id.etPassword1);

        btnLogin1 = findViewById(R.id.btnLogin1);
        btnLogin1.setOnClickListener(this);


//        tvRegister = findViewById(R.id.tvCreateAccount);
//        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin1:
                Username1 = etUsername1.getText().toString();
                Password1 = etPassword1.getText().toString();
                login(Username1,Password1);
                break;
        }
    }

    private void login(String username, String password) {

        apiinterfaceadmin = Apiclientadmin.getClient().create(Apiinterfaceadmin.class);
        Call<Loginadmin> loginCall = apiinterfaceadmin.loginResponse(username,password);
        loginCall.enqueue(new Callback<Loginadmin>() {
            @Override
            public void onResponse(Call<Loginadmin> call, Response<Loginadmin> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    // Ini untuk menyimpan sesi
                    sessionManageradmin = new SessionManageradmin(LoginActivityadmin.this);
                    Logindataadmin logindataadmin = response.body().getLoginData();
                    sessionManageradmin.createLoginSession(logindataadmin);

                    //Ini untuk pindah
                    Toast.makeText(LoginActivityadmin.this, "Selamat datang "+response.body().getLoginData().getUsername(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivityadmin.this, MainActivity5.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivityadmin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Loginadmin> call, Throwable t) {
                Toast.makeText(LoginActivityadmin.this, "Gagal koneksi ke server, coba lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
