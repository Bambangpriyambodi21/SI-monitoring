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

public class MainActivity2 extends AppCompatActivity {
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/EDITGURU.php";
    public static final String URLEDITforum = "https://monitoring11a.000webhostapp.com/PROJEK/EDITF.php";
    SessionManagerguru sessionManagerguru;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        SessionManagerguru sessionManagerguru = new SessionManagerguru(MainActivity2.this);
        if(!sessionManagerguru.isLoggedIn()){
            moveToLogin();
        }

        TextView user = findViewById(R.id.userr);
        Button button14 = findViewById(R.id.button14);
        Button button4 = findViewById(R.id.button4);
        Button buttonprofilguru = findViewById(R.id.buttonprofilguru);
        Button button2 = findViewById(R.id.button2);
        Button button15 = findViewById(R.id.button15);

        String username = sessionManagerguru.getUserDetail().get(SessionManagerguru.USERNAME);
        user.setText(username);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sessionManagerguru.getUserDetail().get(SessionManagerguru.USERNAME);
                forum(username);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, Monitoring.class));

            }
        });

        buttonprofilguru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sessionManagerguru.getUserDetail().get(SessionManagerguru.USERNAME);
                detailpenempatan(username);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, nilaiMainActivity.class));
            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, infopklMainActivitysiswaguru.class));
            }
        });
    }

    private void forum(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String namagx = jObj.getString("namag");


                            Intent intent = new Intent(MainActivity2.this, MainActivityforum.class);
                            intent.putExtra("namag",namagx);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namag", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }

    private void detailpenempatan(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idgx = jObj.getString("idg");
                            String namagx = jObj.getString("namag");
                            String nipgx = jObj.getString("nipg");
                            String telpgx2 = jObj.getString("telpg");
                            String kelingx3 = jObj.getString("keling");
                            String kelingx31 = jObj.getString("keling1");
                            String kelingx32 = jObj.getString("keling2");
                            String kelingx33 = jObj.getString("keling3");
                            String kelingx34 = jObj.getString("keling4");
                            String kelingx35 = jObj.getString("keling5");
                            String kelingx36 = jObj.getString("keling6");
                            String fotogx = jObj.getString("fotog");

                            Intent intent = new Intent(MainActivity2.this, detailguru.class);
                            intent.putExtra("idg",idgx);
                            intent.putExtra("namag",namagx);
                            intent.putExtra("nipg",nipgx);
                            intent.putExtra("telpg",telpgx2);
                            intent.putExtra("keling",kelingx3);
                            intent.putExtra("keling1",kelingx31);
                            intent.putExtra("keling2",kelingx32);
                            intent.putExtra("keling3",kelingx33);
                            intent.putExtra("keling4",kelingx34);
                            intent.putExtra("keling5",kelingx35);
                            intent.putExtra("keling6",kelingx36);
                            intent.putExtra("fotog",fotogx);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namag", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }


    private void moveToLogin() {
        Intent intent = new Intent(MainActivity2.this, LoginActivityguru.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuguru, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogoutguru:
                sessionManagerguru.logoutSession();
                moveToLogin();
        }
        return super.onOptionsItemSelected(item);
    }
}