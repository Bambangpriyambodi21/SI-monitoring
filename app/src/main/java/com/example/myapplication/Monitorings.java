package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class Monitorings extends AppCompatActivity {
    public static final String URLEDIT = "https://monitoring11a.000webhostapp.com/PROJEK/SELECTMS.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitorings);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        SessionManager sessionManager = new SessionManager(Monitorings.this);
        Button button13 = findViewById(R.id.button13);
        Button button15 = findViewById(R.id.button15);

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Monitorings.this, monitoringsMainActivity.class));
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Monitorings.this, monitoringasMainActivity.class));
            }
        });
    }

    private void monitoring(String idpp) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idsx = jObj.getString("ids");
                            String nisx = jObj.getString("nis");
                            String namasx = jObj.getString("namas");
                            String kelasx1 = jObj.getString("kelas");
                            String telpsx2 = jObj.getString("telps");
                            String pembimbingx3 = jObj.getString("pembimbing");
                            String industrix4 = jObj.getString("industri");


                            Intent intent = new Intent(Monitorings.this, monitoringasMainActivity.class);
                            intent.putExtra("ids",idsx);
                            intent.putExtra("nis",nisx);
                            intent.putExtra("namas",namasx);
                            intent.putExtra("kelas",kelasx1);
                            intent.putExtra("telps",telpsx2);
                            intent.putExtra("pembimbing",pembimbingx3);
                            intent.putExtra("industri",industrix4);

                            startActivity(intent);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Monitorings.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namas", idpp);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }
}