package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.apiguru.ApiClientguru;
import com.example.myapplication.apiguru.ApiInterfaceguru;
import com.example.myapplication.modeloguru.loginguru.LoginDataguru;
import com.example.myapplication.modeloguru.loginguru.Loginguru;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityguru extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etPassword;
    Button btnLogin;
    String Username, Password;
    TextView tvRegister;
    ApiInterfaceguru apiInterfaceguru;
    SessionManagerguru sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivityguru);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        etUsername = findViewById(R.id.etUsernameguru);
        etPassword = findViewById(R.id.etPasswordguru);

        btnLogin = findViewById(R.id.btnLoginguru);
        btnLogin.setOnClickListener(this);


//        tvRegister = findViewById(R.id.tvCreateAccount);
//        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginguru:
                Username = etUsername.getText().toString();
                Password = etPassword.getText().toString();
                login(Username,Password);
                break;
        }
    }

    private void login(String username, String password) {

        apiInterfaceguru = ApiClientguru.getClient().create(ApiInterfaceguru.class);
        Call<Loginguru> loginCall = apiInterfaceguru.loginResponse(username,password);
        loginCall.enqueue(new Callback<Loginguru>() {
            @Override
            public void onResponse(Call<Loginguru> call, Response<Loginguru> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    // Ini untuk menyimpan sesi
                    sessionManager = new SessionManagerguru(LoginActivityguru.this);
                    LoginDataguru loginDataguru = response.body().getLoginData();
                    sessionManager.createLoginSession(loginDataguru);

                    //Ini untuk pindah
                    Toast.makeText(LoginActivityguru.this, "Selamat datang "+response.body().getLoginData().getUsername(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivityguru.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivityguru.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Loginguru> call, Throwable t) {
                Toast.makeText(LoginActivityguru.this, "Gagal koneksi ke server, coba lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
