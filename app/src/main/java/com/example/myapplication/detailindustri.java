package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class detailindustri extends AppCompatActivity {
    public static final String URLEDITindustri = "https://monitoring11a.000webhostapp.com/PROJEK/EDITindustri.php";
    TextView idi, namai,bidangi, alamati, telpi,napemi;
    Button home;
    String idiinfo, namaiinfo,bidangiinfo, alamatiinfo, telpiinfo,napemiinfo, industriinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailindustri);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);
        idi = findViewById(R.id.idi);
        namai = findViewById(R.id.namai);
        bidangi = findViewById(R.id.bidangi);
        alamati = findViewById(R.id.alamati);
        telpi = findViewById(R.id.telpi);
        napemi = findViewById(R.id.napemi);

        industriinfo = getIntent().getStringExtra("industri");

        detailindustrii(industriinfo);

    }
    private void detailindustrii(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEDITindustri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);

                            String idix = jObj.getString("idi");
                            String namaix = jObj.getString("namai");
                            String bidangix = jObj.getString("bidangi");
                            String alamatix1 = jObj.getString("alamati");
                            String telpix2 = jObj.getString("telpi");
                            String napemix3 = jObj.getString("napemi");
                            home = findViewById(R.id.button24);

                            home.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    finish();
                                }
                            });


                            idi.setText(idix);
                            namai.setText(namaix);
                            bidangi.setText(bidangix);
                            alamati.setText(alamatix1);
                            telpi.setText(telpix2);
                            napemi.setText(napemix3);
                        }catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(detailindustri.this, "Gagal Koneksi Ke server, Cek setingan koneksi anda", Toast.LENGTH_LONG).show();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("namai", id);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }
}