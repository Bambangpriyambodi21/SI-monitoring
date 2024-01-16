package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/EDITADMIN.php";
    SessionManageradmin sessionManageradmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        SessionManageradmin sessionManageradmin = new SessionManageradmin(MainActivity5.this);
        if(!sessionManageradmin.isLoggedIn()){
            moveToLogin();
        }

        TextView user = findViewById(R.id.userra);
        Button button10 = findViewById(R.id.button10);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button6 = findViewById(R.id.button6);
        Button button5 = findViewById(R.id.button5);
        Button button9 = findViewById(R.id.button9);
        Button button2 = findViewById(R.id.button2);
        Button button51 = findViewById(R.id.button511);
        Button btnprofil = findViewById(R.id.buttonprofiladmin);

        String username = sessionManageradmin.getUserDetail().get(SessionManageradmin.USERNAME);
        user.setText(username);

        btnprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sessionManageradmin.getUserDetail().get(SessionManageradmin.USERNAME);
                detailadmin(username);
            }
        });
        button51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sessionManageradmin.getUserDetail().get(SessionManageradmin.USERNAME);
                forumadmin(username);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity5.this, Monitoring.class));
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity5.this, NilaiPKL.class));
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity5.this, infopklMainActivity.class));
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity5.this, guruMainActivity.class));
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity5.this, industriMainActivity.class));
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity5.this, siswaMainActivity.class));
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity5.this, penempatanMainActivity.class));
            }
        });
    }

    private void detailadmin(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idax = jObj.getString("ida");
                            String userax = jObj.getString("usera");
                            String namaax = jObj.getString("namaa");
                            String telpa2 = jObj.getString("telpa");
                            String fotoax3 = jObj.getString("fotoa");


                            Intent intenti = new Intent(MainActivity5.this, detailadmin.class);
                            intenti.putExtra("ida",idax);
                            intenti.putExtra("usera",userax);
                            intenti.putExtra("namaa",namaax);
                            intenti.putExtra("telpa",telpa2);
                            intenti.putExtra("fotoa",fotoax3);

                            startActivity(intenti);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity5.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namaa", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

    private void forumadmin(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String namaax = jObj.getString("namaa");


                            Intent intenti = new Intent(MainActivity5.this, MainActivityforumadmin.class);
                            intenti.putExtra("namaa",namaax);

                            startActivity(intenti);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity5.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namaa", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }


    private void moveToLogin() {
        Intent intent = new Intent(MainActivity5.this, LoginActivityadmin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogout:
                SessionManageradmin.logoutSession();
                moveToLogin();
        }
        return super.onOptionsItemSelected(item);
    }
}