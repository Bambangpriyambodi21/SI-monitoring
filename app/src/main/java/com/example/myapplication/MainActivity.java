package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String username = "guru";
    String password = "guru";
    String username1 = "siswa";
    String password1 = "siswa";
    String username2 = "admin";
    String password2 = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            EditText txtUser = findViewById(R.id.txtUser);
            EditText txtPass = findViewById(R.id.txtPass);
            Button login = findViewById(R.id.login);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (txtUser.getText().toString().equalsIgnoreCase(username)&& txtPass.getText().toString().equalsIgnoreCase(password)){
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    }
                    else if (txtUser.getText().toString().equalsIgnoreCase(username1)&& txtPass.getText().toString().equalsIgnoreCase(password1)){
                        startActivity(new Intent(MainActivity.this, MainActivity3.class));
                    }
                    else if (txtUser.getText().toString().equalsIgnoreCase(username2)&& txtPass.getText().toString().equalsIgnoreCase(password2)){
                        Intent intent = new Intent(MainActivity.this, MainActivity5.class);
                        intent.putExtra("user",txtUser.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "username/password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
